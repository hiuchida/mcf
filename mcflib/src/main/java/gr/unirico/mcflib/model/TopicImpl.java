package gr.unirico.mcflib.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gr.unirico.mcflib.api.Comment;
import gr.unirico.mcflib.api.Topic;
import gr.unirico.mcflib.exception.IllegalPrevidException;
import gr.unirico.mcflib.impl.CommentComparator;
import gr.unirico.mcflib.util.DateUtil;
import gr.unirico.mcflib.util.DigestBuilder;
import gr.unirico.mcflib.util.ListBuilder;

public class TopicImpl extends NodeImpl implements Topic {
	private static final String RUNNING = "running";
	private static final String COMPLETE = "complete";

	private String status;
	private String url = "";
	private List<Comment> list = new ArrayList<>();

	public TopicImpl(String name) {
		super(name);
		this.status = RUNNING;
	}

	public TopicImpl(String previd, String id, String name, String timestamp, String status) {
		super(previd, id, name, timestamp);
		this.status = status;
	}

	public List<String> toList() {
		ListBuilder lb = newListBuilder(TopicImpl.class);
		lb.append("status", status);
		lb.append("url", url);
		for (Comment _c : list) {
			CommentImpl c = (CommentImpl)_c;
			lb.append(c.toList());
		}
		lb.append("hash", hash);
		return lb.toList();
	}
	
	@Override
	protected String toDigestString() {
		DigestBuilder db = newDigestBuilder(TopicImpl.class);
		db.append("status", status);
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
		c.archive(false, this, getLastid());
		list.add(c);
		this.timestamp = DateUtil.createTimestampStr();
	}
	
	public void addValidate(Comment _c) {
		CommentImpl c = (CommentImpl)_c;
		c.archive(true, this, getLastid());
		list.add(c);
	}
	
	private String getLastid() {
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
		this.status = COMPLETE;
		setArchived(this);
	}

	public synchronized void setUrl(String url) {
		checkArchived();
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public String getStatus() {
		return status;
	}

	public List<Comment> getList() {
		List<Comment> l = new ArrayList<>();
		for (Comment c : list) {
			l.add(c);
		}
		return l;
	}

	public List<Comment> getList(boolean bAsc) {
		List<Comment> l = getList();
		Collections.sort(l, new CommentComparator(bAsc));
		return l;
	}

}
