package gr.unirico.mcflib.util;

import java.lang.invoke.MethodHandles;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DigestUtil {
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	private static final String SHA256 = "SHA-256";

	public static String calcBase64(String s) {
		return calcBase64(SHA256, s.getBytes());
	}

	public static String calcHex(String s) {
		return calcHex(SHA256, s.getBytes());
	}

	private static String calcBase64(String algo, byte[] bytes) {
		try {
			MessageDigest md = MessageDigest.getInstance(algo);
			md.update(bytes);
			return Base64Util.encode(md.digest());
		} catch (NoSuchAlgorithmException e) {
			logger.error("Error in calcBase64", e);
			throw new IllegalArgumentException(e);
		}
	}

	private static String calcHex(String algo, byte[] bytes) {
		try {
			MessageDigest md = MessageDigest.getInstance(algo);
			md.update(bytes);
			StringBuilder sb = new StringBuilder();
			for (byte b : md.digest()) {
				sb.append(String.format("%02x", b & 0xFF));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			logger.error("Error in calcHex", e);
			throw new IllegalArgumentException(e);
		}
	}

}
