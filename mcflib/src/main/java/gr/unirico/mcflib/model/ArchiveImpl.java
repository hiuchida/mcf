package gr.unirico.mcflib.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gr.unirico.mcflib.api.Archive;
import gr.unirico.mcflib.api.Topic;
import gr.unirico.mcflib.exception.IllegalPrevidException;
import gr.unirico.mcflib.impl.TopicComparator;
import gr.unirico.mcflib.util.DateUtil;
import gr.unirico.mcflib.util.DigestBuilder;
import gr.unirico.mcflib.util.ListBuilder;

public class ArchiveImpl extends NodeImpl implements Archive {
	private List<TopicImpl> list = new ArrayList<>();

	public ArchiveImpl(String name) {
		super(name);
	}

	public ArchiveImpl(String previd, String id, String name, String timestamp) {
		super(previd, id, name, timestamp);
	}

	public List<String> toList() {
		ListBuilder lb = newListBuilder(ArchiveImpl.class);
		for (TopicImpl t : list) {
			lb.append(t.toList());
		}
		lb.append("hash", hash);
		return lb.toList();
	}
	
	@Override
	protected synchronized String toDigestString() {
		DigestBuilder db = newDigestBuilder(ArchiveImpl.class);
		for (TopicImpl t : list) {
			db.append("topic", t.getHash());
		}
		return db.toString();
	}
	
	@Override
	public String toString() {
		return this.previd + "," + this.list.toString();
	}
	
	public synchronized void add(Topic _t) {
		TopicImpl t = (TopicImpl)_t;
		t.archive(false, this, getLastid());
		list.add(t);
		this.timestamp = DateUtil.createTimestampStr();
	}
	
	public void addValidate(Topic _t) {
		TopicImpl t = (TopicImpl)_t;
		t.archive(true, this, getLastid());
		list.add(t);
	}
	
	private synchronized String getLastid() {
		if (list.size() == 0) {
			return FIRSTID;
		}
		return list.get(list.size() - 1).getId();
	}
	
	void archive(boolean bValidate, NodeImpl parent, String previd) {
		checkArchived();
		if (bValidate) {
			if (!this.previd.equals(previd)) {
				throw new IllegalPrevidException(this.previd);
			}
		} else {
			this.previd = previd;
			this.timestamp = DateUtil.createTimestampStr();
		}
		setArchived(this);
	}

	public List<Topic> getList() {
		return getList(false);
	}

	public List<Topic> getList(boolean bAsc) {
		List<Topic> l = new ArrayList<>();
		for (Topic t : list) {
			l.add(t);
		}
		Collections.sort(l, new TopicComparator(bAsc));
		return l;
	}

}
