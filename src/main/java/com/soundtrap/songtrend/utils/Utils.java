package com.soundtrap.songtrend.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	
	public final static String SOUNDCLOUD_URL = "https://api.soundcloud.com/tracks?client_id=3a792e628dbaf8054e55f6e134ebe5fa";
	public final static long FETCH_PERIOD = 86400000; //24h in milliseconds
	
	public static Long getDateInMillis(String createDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		try {
			date = (Date)formatter.parse(createDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long mills = date.getTime();
		return mills;
	}
	
	public static boolean isNumeric(String str){
	    for (char c : str.toCharArray()) {
	        if (!Character.isDigit(c)) 
	        	return false;
	    }
	    return true;
	}
	
	public static String getStringDate(Long countDateInMillis) {
		Date date = new Date(countDateInMillis);
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String stringDate = df.format(date);
        return stringDate;
	}
	
	public static String now() {
		DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date now = new Date();
		return sdf.format(now);
	}
}
