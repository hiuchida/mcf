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
		CommentImpl c = new CommentImpl(previd, id, name, timestamp);
		c.setUserid(get("userid"));
		c.setComment(get("comment"));
		String hash = get("hash");
		c.validate(hash);
		return c;
	}

}
