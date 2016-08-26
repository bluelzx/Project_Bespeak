package com.lhfeiyu.tools;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 工具包：通用生成器类 <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 虞荣华 <p>
 * <strong> 编写时间：</strong> 2016年4月12日12:06:03 <p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 http://lhfeiyu.com <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 2.0 <p>
 * <strong> 备&nbsp;&nbsp;&nbsp;&nbsp;注：</strong> 包路径：com.lhfeiyu.tools.CommonGenerator <p>
 */
public class CommonGenerator {
	
	/**
	 * 创建并返回hashMap
	 * @return HashMap new HashMap<String,Object>()
	 */
	public static Map<String,Object> getHashMap(){
		return new HashMap<String,Object>();
	}

	/**
	 * 创建并返回当前日期的Date对象
	 * @return Date new Date()
	 */
	public static Date getDate(){
		return new Date();
	}
	
	/**
	 * 创建并返回当前日期的Calendar对象
	 * @return Calendar Calendar.getInstance()
	 */
	public static Calendar getCalendar(){
		return Calendar.getInstance();
	}
	
	/**
	 * 创建并返回指定日期的Calendar对象
	 * @param date 日期 c.setTime(date)
	 * @return Calendar Calendar.getInstance()
	 */
	public static Calendar getCalendar(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c;
	}
	
	/**
	 * 判断数字是否小于10，如果小于10前面加一个0，否则直接转换为字符串：3："03", 10:"10"
	 * @param num
	 * @return String
	 */
	public static String getTwoLessTen(int num){
		if(num < 10 && num >0){
			return "0"+num;
		}else{
			return ""+num;
		}
	}
	
	/**
	 * 返回字符串序号:前缀+年月日时分秒+100以内随机数+后缀（前缀和后缀皆可省略）
	 * @param prefix 前缀
	 * @param suffix 后缀
	 * @return String 序号字符串
	 */
	public static String getSerialByDate(String prefix,String suffix){
		if(null == prefix)prefix = "";
		if(null == suffix)suffix = "";
		Calendar c = Calendar.getInstance();
		Random r = new Random();
		int random = r.nextInt(100);
		String dateStr = prefix
						+c.get(Calendar.YEAR)+
						getTwoLessTen(c.get(Calendar.MONTH)+1)+
						getTwoLessTen(c.get(Calendar.DAY_OF_MONTH))+
						getTwoLessTen(c.get(Calendar.HOUR_OF_DAY))+
						getTwoLessTen(c.get(Calendar.MINUTE))+
						getTwoLessTen(c.get(Calendar.SECOND))+
						getTwoLessTen(random)+
						suffix;
		return dateStr;
	}
	
	/**
	 * 返回字符串序号:前缀+年月日时分秒+100以内随机数（前缀可省略）
	 * @param prefix 前缀
	 * @return String 序号字符串
	 */
	public static String getSerialByDate(String prefix){
		return getSerialByDate(prefix,"");
	}
	
	/**
	 * 返回字符串序号:年月日时分秒+100以内随机数
	 * @return String 序号字符串
	 */
	public static String getSerialByDate(){
		return getSerialByDate("","");
	}
	
	/**
	 * 根据Date对象得到时间字符串：XX年XX月XX日XX时XX分XX秒
	 * @param date
	 * @return 日期字符串
	 */
	public static String getDateStr(Date date){
		if(null == date)date = new Date();
	    Calendar c = Calendar.getInstance();
	    
		int month = c.get(Calendar.MONTH)+1;
		String monthStr = String.valueOf(month);
		if(month<10)monthStr = "0"+monthStr;
		
		int day = c.get(Calendar.DAY_OF_MONTH);
		String dayStr = String.valueOf(day);
		if(day<10)dayStr = "0"+dayStr;
		
		int hour = c.get(Calendar.HOUR);
		String hourStr = String.valueOf(hour);
		if(hour<10)hourStr = "0"+hourStr;
		
		int minute = c.get(Calendar.MINUTE);
		String minuteStr = String.valueOf(minute);
		if(minute<10)minuteStr = "0"+minuteStr;
		
		int second = c.get(Calendar.SECOND);
		String secondStr = String.valueOf(second);
		if(second<10)secondStr = "0"+secondStr;
		
		String timeStr = ""+c.get(Calendar.YEAR)+"年"+monthStr+"月"+dayStr+"日"+hourStr+"时"+minuteStr+"分"+second+"秒";
		return timeStr;
	}
	
	/**
	 * 根据Date对象得到时间字符串：XX-XX-XX-XX-XX-XX（年月日时分秒）
	 * @param date
	 * @return 日期字符串
	 */
	public static String getDateNumStr(Date date){
		if(null == date)date = new Date();
	    Calendar c = Calendar.getInstance();
	    
		int month = c.get(Calendar.MONTH)+1;
		String monthStr = String.valueOf(month);
		if(month<10)monthStr = "0"+monthStr;
		
		int day = c.get(Calendar.DAY_OF_MONTH);
		String dayStr = String.valueOf(day);
		if(day<10)dayStr = "0"+dayStr;
		
		int hour = c.get(Calendar.HOUR);
		String hourStr = String.valueOf(hour);
		if(hour<10)hourStr = "0"+hourStr;
		
		int minute = c.get(Calendar.MINUTE);
		String minuteStr = String.valueOf(minute);
		if(minute<10)minuteStr = "0"+minuteStr;
		
		int second = c.get(Calendar.SECOND);
		String secondStr = String.valueOf(second);
		if(second<10)secondStr = "0"+secondStr;
		
		String timeStr = ""+c.get(Calendar.YEAR)+"-"+monthStr+"-"+dayStr+"-"+hourStr+"-"+minuteStr+"-"+second;
		return timeStr;
	}
	
	public static String buildOSSZoom(String path, int width, int height, String quality){//@@_OSS_IMG_@@   @120w_120h_4e_50Q  @100w_100h_4e_240-240-246bgc
		if(Check.isNull(quality))quality = "50Q";
		String suffix = "@"+width+"w_"+height+"h_4e_240-240-246bgc_"+quality; 
		if(Check.isNotNull(path))suffix = path+suffix;
		return suffix;
	}
	public static String buildOSSZoom(String path, int width, int height){
		return buildOSSZoom(path, width, height, null);
	}
	public static String buildOSSZoom(int width, int height){
		return buildOSSZoom(null, width, height, null);
	}
	public static String buildOSSZoom(int width, int height, String quality){
		return buildOSSZoom(null, width, height, quality);
	}
	
	public static void main(String[] args) {
		//getSerialByDate();
		
		//String name = ",'~`!@#$%^&*()-_+=[]{}|?<>/\\--321".replaceAll("[/'\"\\;,:-<>]", "");
		//System.out.println(name);
	}
}
