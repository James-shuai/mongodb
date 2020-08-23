package com.cheguansuo.util;

public class BlankUtils {
	
	public static boolean isBlank(Integer val){
		return val==null || val==0;
	}
	
	public static boolean isBlank(Long val){
		return val==null || val==0;
	}
	
	public static boolean isBlank(String val){
		return val==null || "".equals(val);
	}
	
	
	public static boolean isNotBlank(String val){
		return !isBlank(val);
	}
	
	public static boolean isNotBlank(Integer val){
		return val!=null && val!=0;
	}
	
	public static boolean isNotBlank(Long val){
		return val!=null && val!=0;
	}
}
