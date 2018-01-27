package mcflib.parser;

import mcflib.model.History;
import mcflib.model.HistoryBody;

public class HistoryParser extends ListParser {
	public HistoryParser(ListParser lp) {
		super(lp);
	}
	
	public History parse() {
		String previd = get("previd");
		String klass = get("class");
		if (!klass.equals(HistoryBody.class.getName())) {
			throwIllegalLine();
		}
		HistoryBody hb = new HistoryBodyParser(this).parse();
		History h = new History(previd, hb);
		String hash = get("hash");
		h.validate(hash);
		return h;
	}

}
