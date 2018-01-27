package mcf.util;

import java.io.File;

public class FileUtil {
	public static void mkdir(String path) {
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}

}
