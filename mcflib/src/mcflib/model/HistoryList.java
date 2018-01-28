package mcflib.model;

import java.util.ArrayList;
import java.util.List;

import mcflib.util.DigestBuilder;
import mcflib.util.ListBuilder;

public class HistoryList {
	private static final String RUNNING = "running";
	private static final String COMPLETE = "complete";

	private String previd;
	private String status;
	private List<History> list;
	private String hash;

	public HistoryList() {
		this.list = new ArrayList<>();
		this.status = RUNNING;
	}
	
	public List<String> toList() {
		this.hash = toDigestString();
		ListBuilder lb = new ListBuilder(HistoryList.class);
		lb.append("previd", previd);
		lb.append("status", status);
		for (History h : list) {
			lb.append(h.toList());
		}
		lb.append("hash", hash);
		return lb.toList();
	}
	
	@Override
	public String toString() {
		return this.previd + "," + this.status + "," + this.list.toString();
	}
	
	public void add(History h) {
		if (this.status.equals(COMPLETE)) {
			throw new RuntimeException("completed");
		}
		list.add(h);
	}
	
	public String getFirstId() {
		if (list.size() == 0) {
			return null;
		}
		return list.get(0).getId();
	}
	
	public String getLastId() {
		if (list.size() == 0) {
			return null;
		}
		return list.get(list.size() - 1).getId();
	}
	
	public void complete(String previd) {
		this.previd = previd;
		this.status = COMPLETE;
		this.hash = toDigestString();
	}
	
	public void validate(String previd, String status, String hash) {
		this.previd = previd;
		this.status = status;
		this.hash = toDigestString();
		if (!this.hash.equals(hash)) {
			throw new RuntimeException("Illegal hash");
		}
	}

	private String toDigestString() {
		DigestBuilder db = new DigestBuilder(HistoryList.class);
		db.append("previd", previd);
		db.append("status", status);
		for (History h : list) {
			db.append("history", h.getHash());
		}
		return db.toString();
	}
	
	public String getPrevid() {
		return previd;
	}

	public String getStatus() {
		return status;
	}

	public List<History> getList() {
		return list;
	}

	public String getHash() {
		return hash;
	}

}
