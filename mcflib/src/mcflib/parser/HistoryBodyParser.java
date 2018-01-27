package mcflib.parser;

import mcflib.model.HistoryBody;

public class HistoryBodyParser extends ListParser {
	public HistoryBodyParser(ListParser lp) {
		super(lp);
	}
	
	public HistoryBody parse() {
		String id = get("id");
		HistoryBody hb = new HistoryBody(id);
		hb.setTimestamp(get("timestamp"));
		hb.setIpaddr(get("ipaddr"));
		hb.setUserid(get("userid"));
		hb.setDocid(get("docid"));
		hb.setComment(get("comment"));
		hb.setAppdata(get("appdata"));
		return hb;
	}

}
