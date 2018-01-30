package gr.unirico.mcflib.util;

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
			return Base64Util.encode(md.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

}
