package gr.unirico.mcfapp.config;

import gr.unirico.mcflib.api.McfApi;
import gr.unirico.mcflib.api.McfApiFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Configuration
public class McfFactoryConfig {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${mcfapp.factory.data.path}")
	String factoryDataPath;

	@Bean
//	@Lazy
//	@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
	public McfApi mcfApi() {
		logger.info("setup factory.");
		McfApiFactory.init(factoryDataPath);
		return McfApiFactory.getInstance();
	}

}
