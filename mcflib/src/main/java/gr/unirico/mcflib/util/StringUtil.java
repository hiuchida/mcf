package gr.unirico.mcflib.util;

public class StringUtil {
	public static String getShortHash(String hash) {
		if (hash != null && hash.length() > 16) {
			return hash.substring(0, 16) + "...";
		}
		return hash;
	}

}
