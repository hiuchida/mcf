package gr.unirico.mcfapp.config;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import gr.unirico.mcflib.api.McfApi;
import gr.unirico.mcflib.api.McfApiFactory;

@Configuration
public class McfFactoryConfig {
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Value("${mcfapp.factory.data.path}")
	String factoryDataPath;

	@Value("${mcfapp.factory.secret}")
	String factorySecret;

	@Bean
	public McfApi mcfApi() {
		logger.info("setup factory.");
		McfApiFactory.init(factoryDataPath, factorySecret);
		return McfApiFactory.getInstance();
	}

}
