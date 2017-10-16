package com.caizhixiang.util;

import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtils {

	private static final Logger Logger = LoggerFactory.getLogger(DateUtils.class);

	public static final Date str2date(final String dateStr, final DatePatternEnum pattern) {
		Date date = null;
		try {
			DateTimeFormatter dtFormatter = DateTimeFormat.forPattern(pattern.getPattern());
			date = dtFormatter.parseDateTime(dateStr).toDate();
		} catch (Exception ex) {
			Logger.error(ex.getMessage(), ex);
		} 
		return date;
	}

	public static final Date now() {
		return new Date();
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static final String date2str(final Date date, final DatePatternEnum pattern) {
		return new DateTime(date).toString(pattern.getPattern());
	}

	/**
	 * 获取前一天
	 * 
	 * @return
	 */
	public static String getYesterday() {
		Calendar today = Calendar.getInstance();
		today.set(Calendar.DATE, today.get(Calendar.DATE)-1);
		Date date = today.getTime();
		String day = date2str(date, DatePatternEnum.yyyyMMdd);
		return day;
	}

	
	/**
	 * 获取指定的日期
	 * 
	 * @return
	 */
	/**
	 * @param interval 以当前日期为准向前或者向后的天数 如1 代表明天-1代表昨天
	 * @return
	 */
	public static Date getDay(int interval,DatePatternEnum datePattern,Date date) {
		Calendar today = Calendar.getInstance();
		today.setTime(date);
		today.set(Calendar.DATE, today.get(Calendar.DATE)+interval);
		return today.getTime();
	}
	
	public static Date getDay(int interval,DatePatternEnum datePattern) {
		return getDay(interval, datePattern, new Date());
	}
	
	/**
	 * 获取当前时间的间隔日期
	 * @param interval
	 * @return
	 */
	public static Date getDay(int interval) {
		return getDay(interval, DatePatternEnum.yyyyMMddHyphen);
	}
	
	/**获取指定日期的间隔日期
	 * @param day
	 * @param interval
	 * @return
	 */
	public static Date getDay(Date day,int interval) {
		return getDay(interval, DatePatternEnum.yyyyMMddHyphen,day);
	}
	
	
	
}
