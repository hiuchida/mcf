package gr.unirico.mcflib.parser;

import gr.unirico.mcflib.model.ArchiveImpl;
import gr.unirico.mcflib.model.TopicImpl;

public class ArchiveParser extends ListParser {
	public ArchiveParser(ListParser lp) {
		super(lp);
	}

	public ArchiveImpl parse() {
		String previd = get("previd");
		String id = get("id");
		String name = get("name");
		ArchiveImpl a = new ArchiveImpl(id, name);
		String hash;
		while (true) {
			String[] pair = splitLine();
			if (pair[0].equals("class")) {
				if (!pair[1].equals(TopicImpl.class.getName())) {
					throwIllegalLine();
				}
				TopicImpl hl = new TopicParser(this).parse();
				a.add(hl);
			} else if (pair[0].equals("hash")) {
				hash = pair[1];
				break;
			} else {
				throwIllegalLine();
			}
		}
		a.validate(previd, hash);
		return a;
	}

}
