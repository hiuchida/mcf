package mcflib.parser;

import java.util.List;

public class ListParserIterator {
	private List<String> list;
	private int lineNo;
	
	public ListParserIterator(List<String> list) {
		this.list = list;
		this.lineNo = 0;
	}

	public String nextLine() {
		if (lineNo >= list.size()) {
			throw new RuntimeException("List lack");
		}
		return list.get(lineNo++);
	}
	
	public void checkEol() {
		if (lineNo != list.size()) {
			throw new RuntimeException("List remain");
		}
	}
	
	public void throwIllegalLine() {
		if (lineNo <= 0) {
			throw new RuntimeException("List lack");
		}
		String line = list.get(lineNo - 1);
		throw new RuntimeException("Illegal line(" + lineNo + "):" + line);
	}

}
