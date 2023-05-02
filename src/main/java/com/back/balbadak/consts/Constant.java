package com.back.balbadak.consts;

import java.time.format.DateTimeFormatter;

public class Constant {
	public static final int IMAGE_THUMB_WIDTH = 400;
	public static final int IMAGE_THUMB_HEIGHT = 400;
	
	public static final DateTimeFormatter DEFAULT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	public static final DateTimeFormatter DEFAULT_DATETIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	public static final DateTimeFormatter SIMPLE_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");
	public static final DateTimeFormatter SIMPLE_DATETIME_FORMAT = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
	public static final DateTimeFormatter TEXT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
	public static final DateTimeFormatter TEXT_DATETIME_FORMAT = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분");
}
