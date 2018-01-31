package gr.unirico.mcflib.parser;

import gr.unirico.mcflib.model.CommentImpl;
import gr.unirico.mcflib.model.TopicImpl;

public class TopicParser extends ListParser {
	public TopicParser(ListParser lp) {
		super(lp);
	}
	
	public TopicImpl parse() {
		String previd = get("previd");
		String id = get("id");
		String name = get("name");
		String status = get("status");
		TopicImpl hl = new TopicImpl(id, name);
		String hash;
		while (true) {
			String[] pair = splitLine();
			if (pair[0].equals("class")) {
				if (!pair[1].equals(CommentImpl.class.getName())) {
					throwIllegalLine();
				}
				CommentImpl h = new CommentParser(this).parse();
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
