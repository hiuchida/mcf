package gr.unirico.mcflib;

import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import gr.unirico.mcflib.api.Comment;
import gr.unirico.mcflib.api.McfApi;
import gr.unirico.mcflib.api.McfApiFactory;
import gr.unirico.mcflib.api.Topic;

public class Debug {
	public static void main(String[] args) {
		try {
			test(System.out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void test(PrintStream out) throws IOException {
		McfApi api = McfApiFactory.getInstance();
		String id;
		String id2;
		String id3;
		{
			Topic t = api.newTopic("Thread-1");
			Comment c = api.newComment("Comment-1");
			t.add(c);
			api.writeTopic(t);
			id = t.getId();
		}
		{
			List<Topic> list = api.getTopicList();
			out.println(list);
			Topic t = api.readTopic(id);
			out.println(t);
			
			Comment c = api.newComment("Comment-2");
			t.add(c);
			api.writeTopic(t);
		}
		{
			List<Topic> list = api.getTopicList();
			out.println(list);
			Topic t = api.readTopic(list.get(0).getId());
			out.println(t);
			api.archiveTopic(t);
		}
		{
			Topic t = api.newTopic("Thread-2");
			Comment c = api.newComment("Comment-1");
			t.add(c);
			api.writeTopic(t);
			id2 = t.getId();
		}
		{
			List<Topic> list = api.getTopicList();
			out.println(list);
			Topic t = api.readTopic(id2);
			out.println(t);
			api.archiveTopic(t);
		}
		{
			Topic t = api.newTopic("Thread-3");
			api.writeTopic(t);
			id3 = t.getId();
		}
		{
			List<Topic> list = api.getTopicList();
			out.println(list);
			Topic t = api.readTopic(id3);
			out.println(t);
			api.archiveTopic(t);
		}
	}

}
