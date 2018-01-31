package gr.unirico.mcflib.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	private static DateFormat format4timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	public static String createTimestampStr() {
		return createTimestampStr(new Date());
	}

	public static String createTimestampStr(Date date) {
		if (date == null) {
			return "";
		}
		return format4timestamp.format(date);
	}

}
