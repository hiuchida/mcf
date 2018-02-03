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
		String timestamp = get("timestamp");
		String status = get("status");
		TopicImpl t = new TopicImpl(previd, id, name, timestamp, status);
		t.setUrl(get("url"));
		String hash;
		while (true) {
			String[] pair = splitLine();
			if (pair[0].equals("class")) {
				if (!pair[1].equals(CommentImpl.class.getName())) {
					throwIllegalLine();
				}
				CommentImpl c = new CommentParser(this).parse();
				t.addValidate(c);
			} else if (pair[0].equals("hash")) {
				hash = pair[1];
				break;
			} else {
				throwIllegalLine();
			}
		}
		t.validate(hash);
		return t;
	}

}
