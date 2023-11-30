package com.zl.mall.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author coolz
 *
 */
public class DateUtils {

	public static final String DEFAULT_DATE_FORMAT = "yyyyMMdd";

	public static String getCurrentDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		return sdf.format(date);

	}
}
