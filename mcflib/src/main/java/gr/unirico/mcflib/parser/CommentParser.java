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
		CommentImpl h = new CommentImpl(id, name);
		String timestamp = get("timestamp");
		h.setUserid(get("userid"));
		h.setComment(get("comment"));
		String hash = get("hash");
		h.validate(previd, timestamp, hash);
		return h;
	}

}
