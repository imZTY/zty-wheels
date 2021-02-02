package com.zty.framework.util.dataSave;

import java.util.List;

/**
 * 
 * @author Tianyi
 * @Date 2019.04.03
 */
public class CsvDancer<T> extends DataDancer<T> {

	public CsvDancer(List<T> list) {
		super(list);
	}
	

	/**
	 *  @return 文件每一行内容串
	 */
	@Override
	public String rowDance() {
		StringBuffer sb = new StringBuffer();
		
		//int i = 1;
		for(T per : this.getObjList()) {
			sb.append(per.toString());
			//i++;
		}
		
		return sb.toString();
	}

	
	
	

	
}
