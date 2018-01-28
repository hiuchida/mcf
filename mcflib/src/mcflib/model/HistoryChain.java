package mcflib.model;

import java.util.ArrayList;
import java.util.List;

import mcflib.util.DigestBuilder;
import mcflib.util.ListBuilder;

public class HistoryChain {
	private String previd;
	private List<HistoryList> list;
	private String hash;

	public HistoryChain() {
		this.list = new ArrayList<>();
	}
	
	public List<String> toList() {
		this.hash = toDigestString();
		ListBuilder lb = new ListBuilder(HistoryChain.class);
		lb.append("previd", previd);
		for (HistoryList h : list) {
			lb.append(h.toList());
		}
		lb.append("hash", hash);
		return lb.toList();
	}
	
	@Override
	public String toString() {
		return this.previd + "," + this.list.toString();
	}
	
	public void add(HistoryList h) {
		list.add(h);
	}
	
	public String getLastId() {
		if (list.size() == 0) {
			return null;
		}
		return list.get(list.size() - 1).getFirstId();
	}
	
	public void validate(String previd, String hash) {
		this.previd = previd;
		this.hash = toDigestString();
		if (!this.hash.equals(hash)) {
			throw new RuntimeException("Illegal hash");
		}
	}

	private String toDigestString() {
		DigestBuilder db = new DigestBuilder(HistoryList.class);
		db.append("previd", previd);
		for (HistoryList h : list) {
			db.append("history", h.getHash());
		}
		return db.toString();
	}
	
	public String getPrevid() {
		return previd;
	}

	public List<HistoryList> getList() {
		return list;
	}

	public String getHash() {
		return hash;
	}

}
