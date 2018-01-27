package mcflib.model;

import java.util.ArrayList;
import java.util.List;

import mcflib.util.DigestBuilder;

public class HistoryList {
	private String previd;
	private List<History> list;
	private String hash;

	public HistoryList() {
		list = new ArrayList<>();
	}
	
	@Override
	public String toString() {
		return this.previd + "," + this.list.toString();
	}
	
	public void addHistory(History h) {
		if (this.hash != null) {
			throw new RuntimeException("completed");
		}
		list.add(h);
	}
	
	public void complete(String previd) {
		this.previd = previd;
		this.hash = toDigestString();
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
