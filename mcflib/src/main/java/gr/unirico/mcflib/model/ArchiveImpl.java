package gr.unirico.mcflib.model;

import java.util.ArrayList;
import java.util.List;

import gr.unirico.mcflib.util.DigestBuilder;
import gr.unirico.mcflib.util.ListBuilder;
import gr.unirico.mcflib.util.UniqueIdUtil;

public class ArchiveImpl extends NodeImpl {
	private List<TopicImpl> list = new ArrayList<>();

	public ArchiveImpl(String name) {
		this(UniqueIdUtil.generate(), name);
	}

	public ArchiveImpl(String id, String name) {
		super(id, name);
	}

	public List<String> toList() {
		this.hash = toDigestString();
		ListBuilder lb = new ListBuilder(ArchiveImpl.class);
		lb.append("previd", previd);
		lb.append("id", id);
		lb.append("name", name);
		for (TopicImpl t : list) {
			lb.append(t.toList());
		}
		lb.append("hash", hash);
		return lb.toList();
	}
	
	@Override
	public String toString() {
		return this.previd + "," + this.list.toString();
	}
	
	public void add(TopicImpl t) {
		t.checkArchived();
		t.setPrevid(getLastid());
		t.archive(this);
		list.add(t);
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
		DigestBuilder db = new DigestBuilder(TopicImpl.class);
		db.append("previd", previd);
		db.append("id", id);
		db.append("name", name);
		for (TopicImpl t : list) {
			db.append("topic", t.getHash());
		}
		return db.toString();
	}
	
	void setPrevid(String previd) {
		this.previd = previd;
		this.hash = toDigestString();
	}

	public List<TopicImpl> getList() {
		return list;
	}

}
