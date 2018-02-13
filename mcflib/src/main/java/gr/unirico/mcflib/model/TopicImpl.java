package gr.unirico.mcflib.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gr.unirico.mcflib.api.Comment;
import gr.unirico.mcflib.api.Topic;
import gr.unirico.mcflib.impl.CommentComparator;
import gr.unirico.mcflib.util.DateUtil;
import gr.unirico.mcflib.util.DigestBuilder;
import gr.unirico.mcflib.util.ListBuilder;

public class TopicImpl extends NodeImpl implements Topic {
	private String url = "";
	private List<CommentImpl> list = new ArrayList<>();

	public TopicImpl(String name) {
		super(name);
	}

	public TopicImpl(String previd, String prevhash, String id, String name, String timestamp, String status, int proof) {
		super(previd, prevhash, id, name, timestamp, status, proof);
	}

	public List<String> toList() {
		ListBuilder lb = newListBuilder(TopicImpl.class);
		lb.append("url", url);
		if (list.size() > 0) {
			for (CommentImpl c : list) {
				lb.append(c.toList());
			}
		} else {
			lb.appendSeparator(true);
		}
		lb.appendSeparator(true);
		lb.append("hash", hash);
		return lb.toList();
	}
	
	@Override
	protected String toDigestString() {
		DigestBuilder db = newDigestBuilder(TopicImpl.class);
		db.append("url", url);
		for (Comment _c : list) {
			CommentImpl c = (CommentImpl)_c;
			db.append("comment", c.getHash());
		}
		return db.toString();
	}
	
	@Override
	public String toString() {
		return this.previd + "," + this.status + "," + this.list.toString();
	}
	
	public synchronized void add(Comment _c) {
		CommentImpl c = (CommentImpl)_c;
		c.archive(false, this, getLastid(), getLasthash(), getLastproof());
		list.add(c);
		this.timestamp = DateUtil.createTimestampStr();
	}
	
	public void addValidate(Comment _c) {
		CommentImpl c = (CommentImpl)_c;
		c.archive(true, this, getLastid(), getLasthash(), getLastproof());
		list.add(c);
	}
	
	private String getLastid() {
		if (list.size() == 0) {
			return FIRSTID;
		}
		return list.get(list.size() - 1).getId();
	}
	
	private String getLasthash() {
		if (list.size() == 0) {
			return FIRSTHASH;
		}
		return list.get(list.size() - 1).getHash();
	}

	private int getLastproof() {
		if (list.size() == 0) {
			return -1;
		}
		return list.get(list.size() - 1).getProof();
	}

	public synchronized void setUrl(String url) {
		checkArchived();
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public int getListsize() {
		return list.size();
	}

	public List<Comment> getList() {
		return getList(false);
	}

	public List<Comment> getList(boolean bAsc) {
		List<Comment> l = new ArrayList<>();
		for (Comment c : list) {
			l.add(c);
		}
		Collections.sort(l, new CommentComparator(bAsc));
		return l;
	}

}
