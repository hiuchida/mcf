package gr.unirico.mcflib.model;

import java.util.ArrayList;
import java.util.List;

import gr.unirico.mcflib.util.DigestBuilder;
import gr.unirico.mcflib.util.ListBuilder;
import gr.unirico.mcflib.util.UniqueIdUtil;

public class HistoryChainImpl extends NodeImpl {
	private List<HistoryListImpl> list;

	public HistoryChainImpl(String name) {
		this(UniqueIdUtil.generate(), name);
	}
	
	public HistoryChainImpl(String id, String name) {
		super(id, name);
		this.list = new ArrayList<>();
	}
	
	public List<String> toList() {
		this.hash = toDigestString();
		ListBuilder lb = new ListBuilder(HistoryChainImpl.class);
		lb.append("previd", previd);
		lb.append("id", id);
		lb.append("name", name);
		for (HistoryListImpl h : list) {
			lb.append(h.toList());
		}
		lb.append("hash", hash);
		return lb.toList();
	}
	
	@Override
	public String toString() {
		return this.previd + "," + this.list.toString();
	}
	
	public void add(HistoryListImpl hl) {
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
		DigestBuilder db = new DigestBuilder(HistoryListImpl.class);
		db.append("previd", previd);
		db.append("id", id);
		db.append("name", name);
		for (HistoryListImpl h : list) {
			db.append("history", h.getHash());
		}
		return db.toString();
	}
	
	void setPrevid(String previd) {
		this.previd = previd;
		this.hash = toDigestString();
	}

	public List<HistoryListImpl> getList() {
		return list;
	}

}
