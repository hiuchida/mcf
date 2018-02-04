package gr.unirico.mcfapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import gr.unirico.mcflib.api.McfApi;
import gr.unirico.mcflib.api.McfApiFactory;
import gr.unirico.mcflib.util.FileUtil;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		McfApiFactory.init("/var/data/mcfapp");
		McfApi api = McfApiFactory.getInstance();
		String dir = api.getDataDir();
		FileUtil.deleteFiles(dir);
		SpringApplication.run(Application.class, args);
	}

}