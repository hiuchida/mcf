package gr.unirico.mcflib.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
	public static BufferedReader newReader(String path) throws IOException {
		return new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
	}

	public static PrintWriter newWriter(String path) throws IOException {
		return new PrintWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));
	}

	public static List<String> read(BufferedReader br) throws IOException {
		List<String> list = new ArrayList<>();
		while (true) {
			String line = br.readLine();
			if (line == null) {
				break;
			}
			list.add(line);
		}
		return list;
	}

	public static void write(PrintWriter pw, List<String> list) {
		for (String s : list) {
			pw.println(s);
		}
	}

	public static void mkdir(String path) {
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}

}
