package gr.unirico.mcflib.model;

import java.util.ArrayList;
import java.util.List;

import gr.unirico.mcflib.util.DigestBuilder;
import gr.unirico.mcflib.util.ListBuilder;
import gr.unirico.mcflib.util.UniqueIdUtil;

public class HistoryChain extends Node {
	private List<HistoryList> list;

	public HistoryChain() {
		this(UniqueIdUtil.generate());
	}
	
	public HistoryChain(String id) {
		super(id);
		this.list = new ArrayList<>();
	}
	
	public List<String> toList() {
		this.hash = toDigestString();
		ListBuilder lb = new ListBuilder(HistoryChain.class);
		lb.append("previd", previd);
		lb.append("id", id);
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
	
	public void add(HistoryList hl) {
		hl.checkArchived();
		hl.setPrevid(getLastid());
		hl.archive(this);
		list.add(hl);
	}
	
	private String getLastid() {
		if (list.size() == 0) {
			return FIRSTID;
		}
		return list.get(list.size() - 1).getId();
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
		db.append("id", id);
		for (HistoryList h : list) {
			db.append("history", h.getHash());
		}
		return db.toString();
	}
	
	void setPrevid(String previd) {
		this.previd = previd;
		this.hash = toDigestString();
	}

	public List<HistoryList> getList() {
		return list;
	}

}
