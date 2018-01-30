package gr.unirico.mcflib;

import java.io.IOException;
import java.io.PrintStream;

import gr.unirico.mcflib.api.History;
import gr.unirico.mcflib.api.HistoryList;
import gr.unirico.mcflib.api.McfApi;

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
			HistoryList hl = api.newHistoryList("Thread-1");
			History h = api.newHistory("Comment-1");
			hl.add(h);
			api.write(hl);
			id = hl.getId();
		}
		{
			HistoryList hl = api.read(id);
			out.println(hl);
			
			History h = api.newHistory("Comment-2");
			hl.add(h);
			api.write(hl);
		}
		{
			HistoryList hl = api.read(id);
			out.println(hl);
			api.archive(hl);
		}
		{
			HistoryList hl = api.newHistoryList("Thread-2");
			History h = api.newHistory("Comment-1");
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
			HistoryList hl = api.newHistoryList("Thread-3");
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
