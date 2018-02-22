package gr.unirico.mcflib.api;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gr.unirico.mcflib.impl.McfApiImpl;

public class McfApiFactory {
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	private static McfApiImpl singleton;
	private static String secret = "secretKey";
	
	public synchronized static McfApi getInstance() {
		if (singleton == null) {
			logger.info("getInstance: create data");
			singleton = new McfApiImpl("data");
		}
		return singleton;
	}
	
	public synchronized static void init(String dataDir, String secret) {
		if (singleton == null) {
			logger.info("init: create {}", dataDir);
			singleton = new McfApiImpl(dataDir);
		} else {
			logger.info("init: set {}", dataDir);
			singleton.setDataDir(dataDir);
		}
		McfApiFactory.secret = secret;
	}
	
	public synchronized static String getSecret() {
		return secret;
	}
	
}
