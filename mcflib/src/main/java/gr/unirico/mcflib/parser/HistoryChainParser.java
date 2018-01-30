package gr.unirico.mcflib.parser;

import gr.unirico.mcflib.model.HistoryChain;
import gr.unirico.mcflib.model.HistoryList;

public class HistoryChainParser extends ListParser {
	public HistoryChainParser(ListParser lp) {
		super(lp);
	}

	public HistoryChain parse() {
		String previd = get("previd");
		String id = get("id");
		String name = get("name");
		HistoryChain hc = new HistoryChain(id, name);
		String hash;
		while (true) {
			String[] pair = splitLine();
			if (pair[0].equals("class")) {
				if (!pair[1].equals(HistoryList.class.getName())) {
					throwIllegalLine();
				}
				HistoryList hl = new HistoryListParser(this).parse();
				hc.add(hl);
			} else if (pair[0].equals("hash")) {
				hash = pair[1];
				break;
			} else {
				throwIllegalLine();
			}
		}
		hc.validate(previd, hash);
		return hc;
	}

}
