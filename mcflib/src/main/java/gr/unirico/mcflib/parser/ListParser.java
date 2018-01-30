package gr.unirico.mcflib.parser;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gr.unirico.mcflib.model.HistoryChainImpl;
import gr.unirico.mcflib.model.HistoryListImpl;
import gr.unirico.mcflib.model.NodeImpl;

public class ListParser {
	private static Logger logger = LogManager.getLogger(ListParser.class);
	
	public static NodeImpl parse(List<String> list) {
		logger.info("parse count: " + list.size());
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
	
	public NodeImpl selectParser() {
		String klass = get("class");
		if (klass.equals(HistoryListImpl.class.getName())) {
			NodeImpl node = new HistoryListParser(this).parse();
			itr.checkEol();
			return node;
		} else if (klass.equals(HistoryChainImpl.class.getName())) {
			NodeImpl node = new HistoryChainParser(this).parse();
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
