package gr.unirico.mcflib.parser;

import java.util.List;

import gr.unirico.mcflib.model.ArchiveImpl;
import gr.unirico.mcflib.model.NodeImpl;
import gr.unirico.mcflib.model.TopicImpl;

public class ListParser {
	public static synchronized NodeImpl parse(List<String> list) {
		ListParserIterator itr = new ListParserIterator(list);
		return new ListParser(itr).selectParser();
	}
	
	private ListParserIterator itr;
	
	private ListParser(ListParserIterator itr) {
		this.itr = itr;
	}
	
	protected ListParser(ListParser lp) {
		this.itr = lp.itr;
	}
	
	private NodeImpl selectParser() {
		NodeImpl node = null;
		String klass = get("class");
		if (klass.equals(TopicImpl.class.getName())) {
			node = new TopicParser(this).parse();
		} else if (klass.equals(ArchiveImpl.class.getName())) {
			node = new ArchiveParser(this).parse();
		} else {
			throwIllegalLine();
		}
		itr.checkEol();
		return node;
	}

	protected String get(String key) {
		String[] pair = splitLine();
		if (!pair[0].equals(key)) {
			throwIllegalLine();
		}
		return pair[1];
	}
	
	protected String[] splitLine() {
		String line;
		do {
			line = itr.nextLine();
		} while (line.endsWith("---"));
		int idx = line.indexOf(":");
		if (idx < 0) {
			throwIllegalLine();
		}
		String[] pair = new String[2];
		pair[0] = line.substring(0, idx).trim();
		pair[1] = line.substring(idx + 1);
		return pair;
	}
	
	protected void throwIllegalLine() {
		itr.throwIllegalLine();
	}

}
