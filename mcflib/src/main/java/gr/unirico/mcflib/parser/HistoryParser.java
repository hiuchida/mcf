package gr.unirico.mcflib.parser;

import gr.unirico.mcflib.model.History;

public class HistoryParser extends ListParser {
	public HistoryParser(ListParser lp) {
		super(lp);
	}
	
	public History parse() {
		String previd = get("previd");
		String id = get("id");
		String name = get("name");
		History h = new History(id, name);
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
