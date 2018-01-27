package mcflib.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

public class FileUtil {
	public static PrintWriter newWriter(String fname) throws IOException {
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fname), "UTF-8"));
		return pw;
	}

	public static void write(PrintWriter pw, List<String> list) {
		for (String s : list) {
			pw.println(s);
		}
	}

}
