package mcflib.model;

import mcflib.util.DigestBuilder;

public class History {
	private String previd;
	private HistoryBody body;
	private String hash;

	public History(String previd, HistoryBody body) {
		this.previd = previd;
		this.body = body;
		this.hash = toDigestString();
	}

	@Override
	public String toString() {
		return this.body.toString() + "," + this.previd;
	}
	
	private String toDigestString() {
		body.complete();
		DigestBuilder db = new DigestBuilder(History.class);
		db.append("previd", previd);
		db.append("body", body.getHash());
		return db.toString();
	}
	
	public String getId() {
		return body.getId();
	}

	public String getPrevid() {
		return previd;
	}

	public HistoryBody getBody() {
		return body;
	}

	public String getHash() {
		return hash;
	}

}
