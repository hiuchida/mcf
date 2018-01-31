package gr.unirico.mcfapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import gr.unirico.mcflib.api.McfApiFactory;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		McfApiFactory.init("/var/data/mcfapp");
		SpringApplication.run(Application.class, args);
	}

}