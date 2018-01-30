package gr.unirico.mcflib.util;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class Base64Util {
	private static Encoder encoder = Base64.getEncoder();
	private static Decoder decoder = Base64.getDecoder();
	
	public static String encode(byte[] bytes) {
		return encoder.encodeToString(bytes);
	}

	public static byte[] decode(String str) {
		return decoder.decode(str);
	}

}
