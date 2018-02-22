package gr.unirico.mcflib.util;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gr.unirico.mcflib.api.McfApiFactory;

public class DigestBuilder {
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	private StringBuilder sb = new StringBuilder();

	public DigestBuilder(Class<?> klass) {
		append("secret", McfApiFactory.getSecret());
		append("class", klass.getName());
	}
	
	public void append(String key, String value) {
		sb.append(key).append(":").append(value).append(";");
	}

	public void append(String key, int value) {
		sb.append(key).append(":").append(value).append(";");
	}

	@Override
	public String toString() {
		logger.debug("calc: {}", sb.toString());
		return DigestUtil.calcBase64(sb.toString());
	}

}
