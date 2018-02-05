package gr.unirico.mcfapp;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import gr.unirico.mcflib.api.McfApi;
import gr.unirico.mcflib.api.McfApiFactory;
import gr.unirico.mcflib.api.Topic;
import gr.unirico.mcflib.util.FileUtil;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		McfApiFactory.init("/var/data/mcfapp");
		McfApi api = McfApiFactory.getInstance();
		String dir = api.getDataDir();
		FileUtil.deleteFiles(dir);
		try {
			Topic t;
			t = api.newTopic("Yahoo!");
			t.setUrl("https://www.yahoo.co.jp/");
			api.writeTopic(t);
			t = api.newTopic("Google");
			t.setUrl("https://www.google.co.jp/");
			api.writeTopic(t);
			t = api.newTopic("Microsoft");
			t.setUrl("https://www.microsoft.com/ja-jp/");
			api.writeTopic(t);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SpringApplication.run(Application.class, args);
	}

}