package mcflib.util;

import java.util.ArrayList;
import java.util.List;

public class ListBuilder {
	private List<String> list = new ArrayList<>();

	public ListBuilder() {
	}

	public void append(List<String> l) {
		for (String s: l) {
			list.add("\t" + s);
		}
	}

	public void append(String key, String value) {
		list.add(key + ":" + value);
	}

	public List<String> toList() {
		return list;
	}

}
