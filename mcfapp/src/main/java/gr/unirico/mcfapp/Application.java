package gr.unirico.mcfapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import gr.unirico.mcflib.api.Comment;
import gr.unirico.mcflib.api.McfApi;
import gr.unirico.mcflib.api.Topic;
import gr.unirico.mcflib.util.FileUtil;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

	@Autowired
	McfApi api;

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx =  SpringApplication.run(Application.class, args);
		Application app = ctx.getBean(Application.class);
		app.startUpTask();
	}

	public void startUpTask() {
		String dir = api.getDataDir();
		FileUtil.deleteFiles(dir);
		try {
			Topic t;
			Comment c;
			t = api.newTopic("Yahoo! Part1", "https://www.yahoo.co.jp/");
			c = api.newComment("testuser1", "comment1");
			t.add(c);
			c = api.newComment("testuser2", "comment2");
			t.add(c);
			api.writeTopic(t);
			api.archiveTopic(t);
			Thread.sleep(100);
			t = api.newTopic("Google Part1", "https://www.google.co.jp/");
			api.writeTopic(t);
			api.archiveTopic(t);
			Thread.sleep(100);
			t = api.newTopic("Microsoft Part1", "https://www.microsoft.com/ja-jp/");
			api.writeTopic(t);
			api.archiveTopic(t);
			Thread.sleep(100);
			t = api.newTopic("Yahoo! Part2", "https://www.yahoo.co.jp/");
			c = api.newComment("testuser1", "comment1");
			t.add(c);
			c = api.newComment("testuser2", "comment2");
			t.add(c);
			api.writeTopic(t);
			Thread.sleep(100);
			t = api.newTopic("Google Part2", "https://www.google.co.jp/");
			api.writeTopic(t);
			Thread.sleep(100);
			t = api.newTopic("Microsoft Part2", "https://www.microsoft.com/ja-jp/");
			api.writeTopic(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}