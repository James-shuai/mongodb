package com.cheguansuo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat yyyyMMddSdf = new SimpleDateFormat("yyyyMMdd");
	
	public static SimpleDateFormat HHssSdf = new SimpleDateFormat("HHss");
	public static SimpleDateFormat HHmmSdf = new SimpleDateFormat("HHmm");
	public static SimpleDateFormat HHmmssSdf = new SimpleDateFormat("HHmmss");
	public static SimpleDateFormat datetimeSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static SimpleDateFormat datetimeSDF2 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
	
	public static SimpleDateFormat datetimeSDF19 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static SimpleDateFormat yyyyMMddHHmmssSdf = new SimpleDateFormat("yyyyMMdd HHmmss");
	public static SimpleDateFormat yyyyMMdd_HHmmssSdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
	public static SimpleDateFormat yyMMdd_HHmmssSdf = new SimpleDateFormat("yyMMdd-HHmmss");
	
	
	public static SimpleDateFormat yyyy_MMdd_HHmmss = new SimpleDateFormat("yyyy_MMdd_HHmmss");
	  
	public static SimpleDateFormat yyyy_MMdd_HHmmss_sss = new SimpleDateFormat("yyyy_MMdd_HHmmss_sss");
	/**
	 * 10λת����
	 * 
	 * @param str
	 * @return
	 */
	public static Date strToDate10(String str) {
		if (BlankUtils.isNotBlank(str)) {
			try {
				return sdf.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 10λת�ַ�
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToStr10(Date date) {
		if (date == null)
			return null;
		return sdf.format(date);
	}
	
	/**
	 * ��ǰʱ��ĺ���
	 * @return
	 */
	public static Long dateMS(){
		return new Date().getTime();
	}
	
	/**
	 * ��ǰʱ�����
	 * @return
	 */
	public static Long dateSecs(){
		return new Date().getTime()/1000;
	}
	

	/**
	 * ����ʱ�� �����ʱ��2010-10-30 ת����Ϊ2010-10-31 00:00:00
	 * 
	 * @param str
	 * @return
	 */
	public static Date strToEndTime(String str) {
		if (BlankUtils.isBlank(str))
			return null;
		Date date = strToDate10(str);
		date.setDate(date.getDate() + 1);
		return date;
	}
	
	
	/**
	 * 19λ��ת�ַ�
	 * @param date
	 * @return
	 */
	public static String toDateTimeStr19(Date date) {
		if (date == null)
			return null;
		return datetimeSDF19.format(date);
	}
	
	/**
	 * 19λ��ת����
	 * @param str
	 * @return
	 */
	public static Date toDateTime19(String str){
		if (BlankUtils.isNotBlank(str)) {
			try {
				return datetimeSDF19.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static String dateTimeToStrTime(Date date) {
		if (date == null)
			return null;
		return datetimeSDF.format(date);
	}
	
	public static Date strTimeTodateTime(String datetimeStr) {
		if (BlankUtils.isNotBlank(datetimeStr)) {
			try {
				return datetimeSDF.parse(datetimeStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static String dateTimeToStrTime2(Date date) {
		if (date == null)
			return null;
		return datetimeSDF2.format(date);
	}

	/**
	 * ��ȡ��
	 */
	public static Date getMonth(Date date) {
		return new Date(date.getYear(), date.getMonth(), 1, 0, 0, 0);
	}

	/**
	 * �����
	 */
	public static Date addMonth(Date date, int i) {
		Date date2 = (Date) date.clone();
		date2.setMonth(date2.getMonth() + i);
		return date2;
	}

	/**
	 * �����
	 */
	public static Date addDay(Date date, int i) {
		return new Date(date.getTime() + 1000 * 60 * 60 * 24 * i);
	}

	/**
	 * yyyy-MM-dd HH:mm:ss ת����  yyyyMMdd
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static String getDay(String time) throws ParseException {
		Date parse = sdf.parse(time);
		String format = yyyyMMddSdf.format(parse);
		return format;
	}
	

	/**
	 * yyyy-MM-dd HH:mm:ss ת����  yyyyMMdd
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static String getHHmm(String time) throws ParseException {
		
		Date parse = sdf.parse(time);
		String format = HHssSdf.format(parse);
		return format;
	}
	//需要毫秒转成对应的00:00:00时长
	public static String timeDifference(Long diff){
		
		if(diff==0){
	    	return "";
	    }
		
		long temp = 1000*60;
		long ss = diff/1000%60;
		long minutes = (diff/1000 - ss)/60%60;
		long hours =((diff/1000 -ss)/60 -minutes)/60;
		
		//long hours = diff/temp/60/24;
	    //long minutes = diff/temp/60; 
	    
	    
		return hours+":"+ (minutes < 10 ?"0"+minutes:minutes+"")+":"+ss;
	}
	
	/**
	 * 获取昨天的日期
	 * @return 
	 */
	public static Date getYesterday(){
		Calendar cal= Calendar.getInstance();
		cal.add(Calendar.DATE,-1);
		return  cal.getTime();
	}
	
	public static SimpleDateFormat getSimpleDateFormatBy(String match){
		
		return null;
	}

}
