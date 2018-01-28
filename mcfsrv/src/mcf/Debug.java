package mcf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.jsp.JspWriter;

import mcf.conf.DataConfig;
import mcflib.model.History;
import mcflib.model.HistoryBody;
import mcflib.model.HistoryChain;
import mcflib.model.HistoryList;
import mcflib.parser.ListParser;
import mcflib.util.FileUtil;

public class Debug {
	public static void test(JspWriter out) throws IOException {
		{
			HistoryList hl = new HistoryList();
			HistoryBody hb = new HistoryBody();
			History h = new History(hl.getLastId(), hb);
			hl.add(h);
			write(1, hl);
		}
		{
			HistoryList hl = read(1);
			out.println(hl);
			
			HistoryBody hb = new HistoryBody();
			History h = new History(hl.getLastId(), hb);
			hl.add(h);
			hl.complete(null);
			write(2, hl);
		}
		HistoryChain hc = new HistoryChain();
		{
			HistoryList hl = read(2);
			out.println(hl);
			
			hc.add(hl);
			write(1, hc);
		}
		{
			HistoryList hl = new HistoryList();
			HistoryBody hb = new HistoryBody();
			History h = new History(hl.getLastId(), hb);
			hl.add(h);
			hl.complete(hc.getLastId());
			write(3, hl);
		}
		{
			HistoryList hl = read(3);
			out.println(hl);
			
			HistoryChain hc2 = read2(1);
			hc2.add(hl);
			write(2, hc2);
		}
	}

	static void write(int no, HistoryList hl) throws IOException {
		String dataDir = DataConfig.getInstance().getPath();
		try (PrintWriter pw = FileUtil.newWriter(dataDir + "/" + "historyList" + no + ".txt")) {
			FileUtil.write(pw, hl.toList());
		} finally {
		}
	}

	static void write(int no, HistoryChain hc) throws IOException {
		String dataDir = DataConfig.getInstance().getPath();
		try (PrintWriter pw = FileUtil.newWriter(dataDir + "/" + "historyChain" + no + ".txt")) {
			FileUtil.write(pw, hc.toList());
		} finally {
		}
	}

	static HistoryList read(int no) throws IOException {
		String dataDir = DataConfig.getInstance().getPath();
		try (BufferedReader br = FileUtil.newReader(dataDir + "/" + "historyList" + no + ".txt")) {
			List<String> list = FileUtil.read(br);
			Object obj = ListParser.parse(list);
			return (HistoryList)obj;
		} finally {
		}
	}

	static HistoryChain read2(int no) throws IOException {
		String dataDir = DataConfig.getInstance().getPath();
		try (BufferedReader br = FileUtil.newReader(dataDir + "/" + "historyChain" + no + ".txt")) {
			List<String> list = FileUtil.read(br);
			Object obj = ListParser.parse(list);
			return (HistoryChain)obj;
		} finally {
		}
	}

}
