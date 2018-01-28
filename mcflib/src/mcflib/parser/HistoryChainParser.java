package mcflib.parser;

import mcflib.model.HistoryChain;
import mcflib.model.HistoryList;

public class HistoryChainParser extends ListParser {
	public HistoryChainParser(ListParser lp) {
		super(lp);
	}

	public HistoryChain parse() {
		String previd = get("previd");
		HistoryChain hc = new HistoryChain();
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
