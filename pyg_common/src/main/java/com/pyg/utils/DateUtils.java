package com.pyg.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	/**
	 * 获得当前时间距离给定天数零点的毫秒时间
	 * @param amount
	 * @return
	 */
	public static Long getDelayTime(int amount){
		//1 设置当前时间
		Calendar calendar = Calendar.getInstance();
		Date newDate = new Date();
		calendar.setTime(newDate);
		//2 将时分秒设置成0
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		//3 设置指定天数
		calendar.add(Calendar.DATE, amount);
		//4 计算当前时间距离设置日期零点的延迟时间
		return calendar.getTimeInMillis() - newDate.getTime();
	}
	
	
	/**
	 * 当前时间具体明天零点的毫秒时间
	 * @return
	 */
	public static Long getDelayTime(){
		return getDelayTime(1);
	}
	
	
	/**
	 * 获得一天的毫秒值
	 * @return
	 */
	public static Long getOneDay(){
		return 24 * 60 * 60 * 1000L;
	}
	
	/**
	 * 获得几月(两位)
	 * @return
	 */
	public static String getCurrentMonth(){
		//1 设置当前时间
		Calendar calendar = Calendar.getInstance();
		Date newDate = new Date();
		calendar.setTime(newDate);
		
		int m = calendar.get(Calendar.MONTH) + 1;
		if(m < 10){
			return "0" + m;
		}
		return "" + m;
	}
	/**
	 * 获得几号(两位)
	 * @return
	 */
	public static String getCurrentDay(){
		//1 设置当前时间
		Calendar calendar = Calendar.getInstance();
		Date newDate = new Date();
		calendar.setTime(newDate);
		
		int d = calendar.get(Calendar.DATE);
		if(d < 10){
			return "0" + d;
		}
		return "" + d;
	}
	
	
	public static void main(String[] args) {
		System.out.println(getCurrentMonth());
		System.out.println(getCurrentDay());
	}
	
	public static String getCurrentTime() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String currentTime = sdf.format(new Date());
		return currentTime;
	}

}
