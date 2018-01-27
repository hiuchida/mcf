package mcflib.model;

import java.util.List;

import mcflib.util.DigestBuilder;
import mcflib.util.ListBuilder;

public class History {
	private String previd;
	private HistoryBody body;
	private String hash;

	public History(String previd, HistoryBody body) {
		this.previd = previd;
		this.body = body;
		this.hash = toDigestString();
	}

	public List<String> toList() {
		ListBuilder lb = new ListBuilder();
		lb.append("class", History.class.getName());
		lb.append("previd", previd);
		lb.append(body.toList());
		lb.append("hash", hash);
		return lb.toList();
	}
	
	@Override
	public String toString() {
		return "(" + this.body.toString() + "," + this.previd + ")";
	}
	
	public void validate(String hash) {
		if (!this.hash.equals(hash)) {
			throw new RuntimeException("Illegal hash");
		}
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
