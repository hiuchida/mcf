package gr.unirico.mcflib.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DigestBuilder {
	private static Logger logger = LoggerFactory.getLogger(DigestBuilder.class);
	private StringBuilder sb = new StringBuilder();

	public DigestBuilder(Class<?> klass) {
		append("secret", "VPCAxzUeJRNGV7FC");
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
