package mcflib.util;

import java.util.UUID;

public class UniqueIdUtil {
	public static String generate() {
		String id = UUID.randomUUID().toString();
		return id.replaceAll("-", "");
	}

}
