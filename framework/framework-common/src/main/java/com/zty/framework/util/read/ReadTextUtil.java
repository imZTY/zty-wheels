package com.zty.framework.util.read;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 读取 .txt 文件内容的工具类
 * @author Tianyi
 * @Date 2019年5月7日
 */
public class ReadTextUtil {
	
	/**
	 * 返回文件的每一行内容
	 * @param filePath 文件路径
	 * @return
	 * @throws IOException
	 */
	public static List<String> getLineList(String filePath) throws IOException {
		// 创建返回对象
		List<String> rt = new ArrayList<String>();
		
		// 创建 reader
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
		
		// 逐行读取内容
		String temp = reader.readLine();
		while( temp != null && temp != "\n") {
			rt.add(temp);
			temp = reader.readLine();
		}
		
		// 返回读取结果
		return rt;
	}

}
