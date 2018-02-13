package gr.unirico.mcflib.parser;

import gr.unirico.mcflib.model.CommentImpl;

public class CommentParser extends ListParser {
	public CommentParser(ListParser lp) {
		super(lp);
	}
	
	public CommentImpl parse() {
		String previd = get("previd");
		String prevhash = get("prevhash");
		String id = get("id");
		String name = get("name");
		String timestamp = get("timestamp");
		String status = get("status");
		String proof = get("proof");
		CommentImpl c = new CommentImpl(previd, prevhash, id, name, timestamp, status, Integer.parseInt(proof));
		c.setUserid(get("userid"));
		String hash = get("hash");
		c.validate(hash);
		return c;
	}

}
