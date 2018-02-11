package gr.unirico.mcflib.parser;

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
			throw new IllegalStateException("List lack");
		}
		return list.get(lineNo++);
	}
	
	public void checkEol() {
		if (lineNo != list.size()) {
			throw new IllegalStateException("List remain");
		}
	}
	
	public void throwIllegalLine() {
		if (lineNo <= 0) {
			throw new IllegalStateException("List lack");
		}
		String line = list.get(lineNo - 1);
		throw new IllegalStateException("Illegal line(" + lineNo + "):" + line);
	}

}
