package gr.unirico.mcfapp;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import gr.unirico.mcflib.api.Comment;
import gr.unirico.mcflib.api.McfApi;
import gr.unirico.mcflib.api.Topic;
import gr.unirico.mcflib.util.FileUtil;

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
		final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
		logger.info("Creating test data");
		String dir = api.getDataDir();
		FileUtil.deleteFiles(dir);
		try {
			Topic t;
			Comment c;
			t = api.newTopic("Yahoo! Part1", "https://www.yahoo.co.jp/");
			c = api.newComment("テストユーザ1", "コメント1");
			t.add(c);
			Thread.sleep(100);
			c = api.newComment("テストユーザ2", "コメント2");
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
			c = api.newComment("テストユーザ1", "コメント1");
			t.add(c);
			Thread.sleep(100);
			c = api.newComment("テストユーザ2", "コメント2");
			t.add(c);
			api.writeTopic(t);
			Thread.sleep(100);
			t = api.newTopic("Google Part2", "https://www.google.co.jp/");
			api.writeTopic(t);
			Thread.sleep(100);
			t = api.newTopic("Microsoft Part2", "https://www.microsoft.com/ja-jp/");
			api.writeTopic(t);
			logger.info("Complete test data");
		} catch (Exception e) {
			logger.error("Error in startUpTask", e);
		}
	}
}