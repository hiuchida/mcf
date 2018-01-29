package mcflib;

import java.io.IOException;
import java.io.PrintStream;

import mcflib.api.McfApi;
import mcflib.model.History;
import mcflib.model.HistoryList;

public class Debug {
	public static void main(String[] args) {
		try {
			test(System.out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void test(PrintStream out) throws IOException {
		McfApi api = McfApi.getInstance();
		String id;
		String id2;
		String id3;
		{
			HistoryList hl = api.newHistoryList();
			History h = api.newHistory();
			hl.add(h);
			api.write(hl);
			id = hl.getId();
		}
		{
			HistoryList hl = api.read(id);
			out.println(hl);
			
			History h = api.newHistory();
			hl.add(h);
			api.write(hl);
		}
		{
			HistoryList hl = api.read(id);
			out.println(hl);
			api.archive(hl);
		}
		{
			HistoryList hl = api.newHistoryList();
			History h = api.newHistory();
			hl.add(h);
			api.write(hl);
			id2 = hl.getId();
		}
		{
			HistoryList hl = api.read(id2);
			out.println(hl);
			api.archive(hl);
		}
		{
			HistoryList hl = api.newHistoryList();
			api.write(hl);
			id3 = hl.getId();
		}
		{
			HistoryList hl = api.read(id3);
			out.println(hl);
			api.archive(hl);
		}
	}

}
