package gr.unirico.mcflib.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {
	private static TimeZone jst = TimeZone.getTimeZone("JST");
	private static Locale jp = Locale.JAPANESE;

	public static String createTimestampStr() {
		Calendar c = Calendar.getInstance(jst, jp);
		return createTimestampStr(c.getTime());
	}

	public static String createTimestampStr(Date date) {
		if (date == null) {
			return "";
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ", jp);
		df.setTimeZone(jst);
		return df.format(date);
	}

}
