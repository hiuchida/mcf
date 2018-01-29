package mcflib.parser;

import mcflib.model.History;
import mcflib.model.HistoryList;

public class HistoryListParser extends ListParser {
	public HistoryListParser(ListParser lp) {
		super(lp);
	}
	
	public HistoryList parse() {
		String previd = get("previd");
		String id = get("id");
		String status = get("status");
		HistoryList hl = new HistoryList(id);
		String hash;
		while (true) {
			String[] pair = splitLine();
			if (pair[0].equals("class")) {
				if (!pair[1].equals(History.class.getName())) {
					throwIllegalLine();
				}
				History h = new HistoryParser(this).parse();
				hl.add(h);
			} else if (pair[0].equals("hash")) {
				hash = pair[1];
				break;
			} else {
				throwIllegalLine();
			}
		}
		hl.validate(previd, status, hash);
		return hl;
	}

}
