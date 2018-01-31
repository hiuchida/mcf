package gr.unirico.mcflib.parser;

import gr.unirico.mcflib.model.CommentImpl;

public class CommentParser extends ListParser {
	public CommentParser(ListParser lp) {
		super(lp);
	}
	
	public CommentImpl parse() {
		String previd = get("previd");
		String id = get("id");
		String name = get("name");
		String timestamp = get("timestamp");
		CommentImpl h = new CommentImpl(id, name);
		h.setIpaddr(get("ipaddr"));
		h.setUserid(get("userid"));
		h.setDocid(get("docid"));
		h.setComment(get("comment"));
		h.setAppdata(get("appdata"));
		String hash = get("hash");
		h.validate(previd, timestamp, hash);
		return h;
	}

}
