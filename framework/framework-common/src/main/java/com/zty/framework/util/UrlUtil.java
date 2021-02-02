package com.zty.framework.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author tianyi
 * @date 2020-12-21 09:41
 */
public class UrlUtil {

    /**
     * 将Map转成url参数
     * @param map
     * @return
     */
    public static String parseMapToUrlString(Map<Object, Object> map){
        StringBuilder sb = new StringBuilder();
        map.forEach((k,v)->{
            try {
                sb.append("&" + URLEncoder.encode(k.toString(), "UTF-8") + "=" + URLEncoder.encode(v.toString(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                sb.append("&" + k.toString() + "=" + v.toString());
            }
        });
        sb.deleteCharAt(0);
        return sb.toString();
    }
}
