package gr.unirico.mcflib.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtil {
	public static final String SHA256 = "SHA-256";
	
	public static String calcBase64(String algo, byte[] bytes) {
		try {
			MessageDigest md = MessageDigest.getInstance(algo);
			md.update(bytes);
			return Base64Util.encode(md.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	public static String calcHex(String algo, byte[] bytes) {
		try {
			MessageDigest md = MessageDigest.getInstance(algo);
			md.update(bytes);
			StringBuilder sb = new StringBuilder();
			for (byte b : md.digest()) {
				sb.append(String.format("%02x", b & 0xFF));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

}
