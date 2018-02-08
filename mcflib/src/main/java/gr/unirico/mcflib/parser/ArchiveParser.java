package gr.unirico.mcflib.parser;

import gr.unirico.mcflib.model.ArchiveImpl;
import gr.unirico.mcflib.model.TopicImpl;

public class ArchiveParser extends ListParser {
	public ArchiveParser(ListParser lp) {
		super(lp);
	}

	public ArchiveImpl parse() {
		String previd = get("previd");
		String prevhash = get("prevhash");
		String id = get("id");
		String name = get("name");
		String timestamp = get("timestamp");
		String status = get("status");
		ArchiveImpl a = new ArchiveImpl(previd, prevhash, id, name, timestamp, status);
		String hash;
		while (true) {
			String[] pair = splitLine();
			if (pair[0].equals("class")) {
				if (!pair[1].equals(TopicImpl.class.getName())) {
					throwIllegalLine();
				}
				TopicImpl t = new TopicParser(this).parse();
				a.addValidate(t);
			} else if (pair[0].equals("hash")) {
				hash = pair[1];
				break;
			} else {
				throwIllegalLine();
			}
		}
		a.validate(hash);
		return a;
	}

}
