package mcflib.util;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtil {
	public static final String SHA512 = "SHA-512";
	
	public static String calc(String algo, ByteArrayOutputStream baos) {
		return calc(algo, baos.toByteArray());
	}

	public static String calc(String algo, byte[] bytes) {
		try {
			MessageDigest md = MessageDigest.getInstance(algo);
			md.update(bytes);
			StringBuilder sb = new StringBuilder();
			for (byte b : md.digest()) {
				String hex = String.format("%02x", b);
				sb.append(hex);
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

}
