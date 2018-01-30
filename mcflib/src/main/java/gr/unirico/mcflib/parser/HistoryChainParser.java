package gr.unirico.mcflib.parser;

import gr.unirico.mcflib.model.HistoryChainImpl;
import gr.unirico.mcflib.model.HistoryListImpl;

public class HistoryChainParser extends ListParser {
	public HistoryChainParser(ListParser lp) {
		super(lp);
	}

	public HistoryChainImpl parse() {
		String previd = get("previd");
		String id = get("id");
		String name = get("name");
		HistoryChainImpl hc = new HistoryChainImpl(id, name);
		String hash;
		while (true) {
			String[] pair = splitLine();
			if (pair[0].equals("class")) {
				if (!pair[1].equals(HistoryListImpl.class.getName())) {
					throwIllegalLine();
				}
				HistoryListImpl hl = new HistoryListParser(this).parse();
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
