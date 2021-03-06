package gr.unirico.mcflib.util;

import java.util.ArrayList;
import java.util.List;

public class ListBuilder {
	private List<String> list = new ArrayList<>();

	public ListBuilder(Class<?> klass) {
		appendSeparator(false);
		append("class", klass.getName());
	}

	public void append(List<String> l) {
		for (String s: l) {
			list.add("\t" + s);
		}
	}

	public void append(String key, String value) {
		list.add(key + ":" + value);
	}

	public void append(String key, int value) {
		list.add(key + ":" + value);
	}

	public void appendSeparator(boolean bTab) {
		if (bTab) {
			list.add("\t---");
		} else {
			list.add("---");
		}
	}

	public List<String> toList() {
		return list;
	}

}
