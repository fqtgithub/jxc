package com.fqt.project.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * @author eye
 *
 */
public class DateUtil {
	
	public static String DatetoString(Date date) throws Exception {
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}

}
