package mcflib.parser;

import mcflib.model.History;

public class HistoryParser extends ListParser {
	public HistoryParser(ListParser lp) {
		super(lp);
	}
	
	public History parse() {
		String previd = get("previd");
		String id = get("id");
		History h = new History(id);
		h.setTimestamp(get("timestamp"));
		h.setIpaddr(get("ipaddr"));
		h.setUserid(get("userid"));
		h.setDocid(get("docid"));
		h.setComment(get("comment"));
		h.setAppdata(get("appdata"));
		String hash = get("hash");
		h.validate(previd, hash);
		return h;
	}

}
