package gr.unirico.mcflib.model;

import java.util.ArrayList;
import java.util.List;

import gr.unirico.mcflib.util.DigestBuilder;
import gr.unirico.mcflib.util.ListBuilder;
import gr.unirico.mcflib.util.UniqueIdUtil;

public class HistoryList extends Node {
	private static final String RUNNING = "running";
	private static final String COMPLETE = "complete";

	private String status;
	private List<History> list;

	public HistoryList() {
		this(UniqueIdUtil.generate());
	}
	
	public HistoryList(String id) {
		super(id);
		this.status = RUNNING;
		this.list = new ArrayList<>();
	}
	
	public List<String> toList() {
		this.hash = toDigestString();
		ListBuilder lb = new ListBuilder(HistoryList.class);
		lb.append("previd", previd);
		lb.append("id", id);
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
		h.checkArchived();
		h.setPrevid(getLastid());
		h.archive(this);
		list.add(h);
	}
	
	private String getLastid() {
		if (list.size() == 0) {
			return FIRSTID;
		}
		return list.get(list.size() - 1).getId();
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
		db.append("id", id);
		db.append("status", status);
		for (History h : list) {
			db.append("history", h.getHash());
		}
		return db.toString();
	}
	
	void setPrevid(String previd) {
		this.previd = previd;
		this.status = COMPLETE;
		this.hash = toDigestString();
	}

	public String getStatus() {
		return status;
	}

	public List<History> getList() {
		return list;
	}

}
