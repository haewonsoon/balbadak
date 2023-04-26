package com.back.balbadak.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.back.balbadak.consts.Constant;

public class DateTimeUtils {
	
	public static String nowDateTime() {
		return LocalDateTime.now().format(Constant.DEFAULT_DATETIME_FORMAT);
	}

	public static String nowSimpleDateTime() {
		return LocalDateTime.now().format(Constant.SIMPLE_DATETIME_FORMAT);
	}
	
	public static String nowDate() {
		return LocalDate.now().format(Constant.DEFAULT_DATE_FORMAT);
	}
	
	public static String nowSimpleDate() {
		return LocalDate.now().format(Constant.SIMPLE_DATE_FORMAT);
	}

	public static int nowYear() {
		return LocalDate.now().getYear();
	}
	
	public static int nowMonth() {
		return LocalDate.now().getMonthValue();
	}
	
	public static int nowDay() {
		return LocalDate.now().getDayOfMonth();
	}
	
	

}
