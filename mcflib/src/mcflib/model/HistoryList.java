package mcflib.model;

import java.util.ArrayList;
import java.util.List;

import mcflib.util.DigestBuilder;
import mcflib.util.ListBuilder;

public class HistoryList {
	private String previd;
	private List<History> list;
	private String hash;

	public HistoryList() {
		list = new ArrayList<>();
	}
	
	public List<String> toList() {
		ListBuilder lb = new ListBuilder();
		lb.append("class", HistoryList.class.getName());
		lb.append("previd", previd);
		for (History h : list) {
			lb.append(h.toList());
		}
		lb.append("hash", hash);
		return lb.toList();
	}
	
	@Override
	public String toString() {
		return this.previd + "," + this.list.toString();
	}
	
	public void add(History h) {
		if (this.hash != null) {
			throw new RuntimeException("completed");
		}
		list.add(h);
	}
	
	public void complete(String previd) {
		this.previd = previd;
		this.hash = toDigestString();
	}
	
	public void validate(String hash) {
		if (!this.hash.equals(hash)) {
			throw new RuntimeException("Illegal hash");
		}
	}

	private String toDigestString() {
		DigestBuilder db = new DigestBuilder(HistoryList.class);
		db.append("previd", previd);
		for (History h : list) {
			db.append("history", h.getHash());
		}
		return db.toString();
	}
	
	public String getPrevid() {
		return previd;
	}

	public String getHash() {
		return hash;
	}

}
