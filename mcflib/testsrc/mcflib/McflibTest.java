package mcflib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;

import mcflib.model.History;
import mcflib.model.HistoryChain;
import mcflib.model.HistoryList;
import mcflib.parser.ListParser;
import mcflib.util.FileUtil;

public class McflibTest {
	public static void test(PrintStream out) throws IOException {
		{
			HistoryList hl = new HistoryList();
			History h = new History();
			hl.add(h);
			write(1, hl);
		}
		{
			HistoryList hl = read(1);
			out.println(hl);
			
			History h = new History();
			hl.add(h);
			write(2, hl);
		}
		{
			HistoryList hl = read(2);
			out.println(hl);
			
			HistoryChain hc = new HistoryChain();
			hc.add(hl);
			write(1, hc);
		}
		{
			HistoryList hl = new HistoryList();
			History h = new History();
			hl.add(h);
			write(3, hl);
		}
		{
			HistoryList hl = read(3);
			out.println(hl);
			
			HistoryChain hc = read2(1);
			hc.add(hl);
			write(2, hc);
		}
	}

	static void write(int no, HistoryList hl) throws IOException {
		String dataDir = getPath();
		try (PrintWriter pw = FileUtil.newWriter(dataDir + "/" + "historyList" + no + ".txt")) {
			FileUtil.write(pw, hl.toList());
		} finally {
		}
	}

	static void write(int no, HistoryChain hc) throws IOException {
		String dataDir = getPath();
		try (PrintWriter pw = FileUtil.newWriter(dataDir + "/" + "historyChain" + no + ".txt")) {
			FileUtil.write(pw, hc.toList());
		} finally {
		}
	}

	static HistoryList read(int no) throws IOException {
		String dataDir = getPath();
		try (BufferedReader br = FileUtil.newReader(dataDir + "/" + "historyList" + no + ".txt")) {
			List<String> list = FileUtil.read(br);
			Object obj = ListParser.parse(list);
			return (HistoryList)obj;
		} finally {
		}
	}

	static HistoryChain read2(int no) throws IOException {
		String dataDir = getPath();
		try (BufferedReader br = FileUtil.newReader(dataDir + "/" + "historyChain" + no + ".txt")) {
			List<String> list = FileUtil.read(br);
			Object obj = ListParser.parse(list);
			return (HistoryChain)obj;
		} finally {
		}
	}

	static String getPath() {
		return ".";
	}

	public static void main(String[] args) throws IOException {
		test(System.out);
	}

}
