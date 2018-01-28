package mcflib.parser;

import java.util.List;

import mcflib.model.HistoryChain;
import mcflib.model.HistoryList;
import mcflib.model.Node;

public class ListParser {
	public static Node parse(List<String> list) {
		ListParserIterator itr = new ListParserIterator(list);
		return new ListParser(itr).selectParser();
	}
	
	private ListParserIterator itr;
	
	public ListParser(ListParserIterator itr) {
		this.itr = itr;
	}
	
	public ListParser(ListParser lp) {
		this.itr = lp.itr;
	}
	
	public Node selectParser() {
		String klass = get("class");
		if (klass.equals(HistoryList.class.getName())) {
			Node node = new HistoryListParser(this).parse();
			itr.checkEol();
			return node;
		} else if (klass.equals(HistoryChain.class.getName())) {
			Node node = new HistoryChainParser(this).parse();
			itr.checkEol();
			return node;
		} else {
			throwIllegalLine();
		}
		return null;
	}

	public String get(String key) {
		String[] pair = splitLine();
		if (!pair[0].equals(key)) {
			throwIllegalLine();
		}
		return pair[1];
	}
	
	public String[] splitLine() {
		String line = itr.nextLine();
		int idx = line.indexOf(":");
		if (idx < 0) {
			throwIllegalLine();
		}
		String[] pair = new String[2];
		pair[0] = line.substring(0, idx).trim();
		pair[1] = line.substring(idx + 1);
		return pair;
	}
	
	public void throwIllegalLine() {
		itr.throwIllegalLine();
	}

}
