package com.zty.common.util.request;

import org.apache.http.*;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.*;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * @author Tianyi
 * @Date 2019年4月17日
 */
public class ApacheHttpRequest extends HttpRequestor {

	public ApacheHttpRequest() {
		
	}
	
	public ApacheHttpRequest(Map<String, String> map) {
		super.headers = map;
	}

	// 代理服务器
	final static String proxyHost = "http-dyn.abuyun.com";
	final static Integer proxyPort = 9020;

	// 代理隧道验证信息
	private String proxyUser = "H0569V6214K1LEOD";
	private String proxyPass = "72BEF6E92B3F46DA";

	private PoolingHttpClientConnectionManager cm = null;
	private HttpHost proxy = null;

	private CredentialsProvider credsProvider = null;
	private RequestConfig reqConfig = null;

	private boolean isIPDynamic = false;

	AuthCache authCache = new BasicAuthCache();
	HttpClientContext localContext = HttpClientContext.create();


	private CloseableHttpClient init(){
		ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
		LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();

		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", plainsf)
				.register("https", sslsf)
				.build();

		cm = new PoolingHttpClientConnectionManager(registry);
		cm.setMaxTotal(20);
		cm.setDefaultMaxPerRoute(5);

		proxy = new HttpHost(proxyHost, proxyPort, "http");

		credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(proxyUser, proxyPass));

		// 设置超时时间
		reqConfig = RequestConfig.custom()
				.setConnectionRequestTimeout(10000)
				.setConnectTimeout(10000)
				.setSocketTimeout(10000)
				.setExpectContinueEnabled(false)
				.setProxy(new HttpHost(proxyHost, proxyPort))
				.build();

		authCache.put(proxy, new BasicScheme());
		localContext.setAuthCache(authCache);

		return  HttpClients.custom().setConnectionManager(cm)
									.setConnectionManagerShared(true)
									.setDefaultCredentialsProvider(credsProvider)
									.build();
	}

	public ApacheHttpRequest initDynamicIP(String username, String password){
		this.proxyUser = username;
		this.proxyPass = password;

		try{
			CloseableHttpClient httpClient = init();

			HttpGet httpGet = new HttpGet("https://test.abuyun.com");
			httpGet.setConfig(reqConfig);
			HttpResponse response = httpClient.execute(httpGet, localContext);

			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while((line = rd.readLine()) != null) {
				System.out.println(line);
			}

			if (response.getStatusLine().getStatusCode() == 200){
				isIPDynamic = true;
			}else {
				isIPDynamic = false;
			}
			((CloseableHttpResponse) response).close();
			httpClient.close();
		}catch (Exception e){
			e.printStackTrace();
		}

		return this;

	}


	@Override
	@SuppressWarnings("deprecation")
	public String sendPost(String url, String param) throws Exception {
		HttpClient httpClient = null;
		if (!isIPDynamic) {
			httpClient = new DefaultHttpClient();

			// 设置超时时间
			httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 8000);
			httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 8000);
		}else{
			httpClient = HttpClients.custom()
					.setConnectionManager(cm)
					.setDefaultCredentialsProvider(credsProvider)
					.build();
		}
		
        HttpPost post = new HttpPost(url);
        // 构造消息头
        Iterator<String> iterator =  headers.keySet().iterator();
        while(iterator.hasNext()) {
        	String header = iterator.next();
        	post.setHeader(header, headers.get(header));
        }
        
        new URLEncodedUtils();
		post.setEntity(new UrlEncodedFormEntity(URLEncodedUtils.parse(param, Charset.forName("UTF-8")), Charset.forName("UTF-8")));
        
        String rt = "";
        try {
        	// 发送请求
			HttpResponse response = null;
			if (!isIPDynamic) {
				response = httpClient.execute(post);
			}else {
				post.setConfig(reqConfig);
				response = httpClient.execute(post, localContext);
			}
			// 检验返回码
	        int statusCode = response.getStatusLine().getStatusCode();
	        if(statusCode != HttpStatus.SC_OK){
	            System.out.println("请求出错: "+statusCode);
				Header[] headers = response.getAllHeaders();  // 请求出错则打印响应头
				for (Header header : headers){
					System.out.println(header.getName() + " : " + header.getValue());
				}
				rt = statusCode+"";  // 请求出错则返回错误代码
	        }else{
	            rt = EntityUtils.toString(response.getEntity());
	        }
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			httpClient.getConnectionManager().shutdown();
		}

        return rt;
	}

	@SuppressWarnings({ "resource", "deprecation" })
	@Override
	public byte[] sendGet(String url, String param) throws Exception {

		HttpClient httpClient = null;
		if (!isIPDynamic) {
			httpClient = new DefaultHttpClient();

			// 设置代理，其实不用这样设置，看：【https://blog.csdn.net/hexieshangwang/article/details/53940130】
//		((AbstractHttpClient) httpClient).getCredentialsProvider().setCredentials(
//			new AuthScope("127.0.0.1", 8888), new UsernamePasswordCredentials("", ""));
//		HttpHost proxy = new HttpHost("127.0.0.1", 8888);
//		httpClient.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, proxy);

			// 设置超时时间
			httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
			httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 10000);
		}else{
//			httpClient = HttpClients.custom()
//					.setConnectionManager(cm)
//					.setConnectionManagerShared(true)
//					.setDefaultCredentialsProvider(credsProvider)
//					.build();
			httpClient = init();
		}
		
        HttpGet httpGet = new HttpGet(url + param);
        // 构造消息头
		if (headers != null) {
			Iterator<String> iterator = headers.keySet().iterator();
			while (iterator.hasNext()) {
				String header = iterator.next();
				httpGet.setHeader(header, headers.get(header));
			}
		}
        
        byte[] rt = null;
        try {
        	// 发送请求
			HttpResponse response = null;
			if (!isIPDynamic) {
				response = httpClient.execute(httpGet);
			}else {
				httpGet.setConfig(reqConfig);
				authCache.put(proxy, new BasicScheme());
				localContext.setAuthCache(authCache);
				response = httpClient.execute(httpGet, localContext);
			}

//			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//			String line = "";
//			while((line = rd.readLine()) != null) {
//				System.out.println(line);
//			}

			// 检验返回码
	        int statusCode = response.getStatusLine().getStatusCode();
	        HttpEntity entity = response.getEntity();
	        if(statusCode != HttpStatus.SC_OK){
	            System.out.println("请求出错: "+statusCode);
	            Header[] headers = response.getAllHeaders();
				for (Header header : headers){
					System.out.println(header.getName() + " : " + header.getValue());
				}
	            rt = (statusCode+"").getBytes();  // 请求出错则返回错误代码
	        }else{
//	        	Thread.sleep(4000);
	        	InputStream inStream = entity.getContent();

	        	List<Byte> list = new ArrayList<Byte>();
        		Integer r = inStream.read();
        		while(r != -1) {
        			list.add(r.byteValue());
        			r = inStream.read();
        		}
        		byte[] b = new byte[list.size()];
        		int bi = 0;
        		Iterator<Byte> iterator_byte = list.iterator();
        		while(iterator_byte.hasNext()) {
        			b[bi] = iterator_byte.next();
        			bi++;
        		}
        		// System.out.println(b.length);
				rt = b;

				inStream.close();
	        }
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
        
        return rt;
	}

}
