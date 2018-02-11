package gr.unirico.mcflib.util;

public class DigestBuilder {
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
		return DigestUtil.calcBase64(DigestUtil.SHA256, sb.toString().getBytes());
	}

}
