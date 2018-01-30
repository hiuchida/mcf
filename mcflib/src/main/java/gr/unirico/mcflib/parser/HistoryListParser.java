package gr.unirico.mcflib.parser;

import gr.unirico.mcflib.model.HistoryImpl;
import gr.unirico.mcflib.model.HistoryListImpl;

public class HistoryListParser extends ListParser {
	public HistoryListParser(ListParser lp) {
		super(lp);
	}
	
	public HistoryListImpl parse() {
		String previd = get("previd");
		String id = get("id");
		String name = get("name");
		String status = get("status");
		HistoryListImpl hl = new HistoryListImpl(id, name);
		String hash;
		while (true) {
			String[] pair = splitLine();
			if (pair[0].equals("class")) {
				if (!pair[1].equals(HistoryImpl.class.getName())) {
					throwIllegalLine();
				}
				HistoryImpl h = new HistoryParser(this).parse();
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
