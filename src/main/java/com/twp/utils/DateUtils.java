package com.twp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期处理
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月21日 下午12:53:33
 */
public class DateUtils {
	/** 时间格式(yyyy-MM-dd) */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	/** 时间格式(yyyy-MM-dd HH:mm:ss) */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }
    
    /**
	 * 获取当前时间的时间戳，单位秒
	 * @return
	 */
	public static int time(){
		Long time = System.currentTimeMillis()/1000;
		return time.intValue();
	}
	
	/**
	 * 格式化时间戳成日期时间
	 * @param timestamp
	 * @param format
	 * @return
	 */
	public static String date(int timestamp,String... format){
		if(timestamp==0){
			return " - "; //无数据
		}
		String formatString = "yyyy-MM-dd HH:mm:ss";
		if(format.length==1){
			formatString = format[0];
		}
		SimpleDateFormat sdf = new SimpleDateFormat(formatString);
		return sdf.format(new Date(timestamp*1000L));
	}
	
}
