package gr.unirico.mcflib.model;

import java.util.ArrayList;
import java.util.List;

import gr.unirico.mcflib.api.Comment;
import gr.unirico.mcflib.api.Topic;
import gr.unirico.mcflib.util.DateUtil;
import gr.unirico.mcflib.util.DigestBuilder;
import gr.unirico.mcflib.util.ListBuilder;
import gr.unirico.mcflib.util.UniqueIdUtil;

public class TopicImpl extends NodeImpl implements Topic {
	private static final String RUNNING = "running";
	private static final String COMPLETE = "complete";

	private String timestamp = "";
	private String url = "";
	private String status = RUNNING;
	private List<Comment> list = new ArrayList<>();

	public TopicImpl(String name) {
		this(UniqueIdUtil.generate(), name);
	}

	public TopicImpl(String id, String name) {
		super(id, name);
	}

	public List<String> toList() {
		this.hash = toDigestString();
		ListBuilder lb = new ListBuilder(TopicImpl.class);
		lb.append("previd", previd);
		lb.append("id", id);
		lb.append("name", name);
		lb.append("timestamp", timestamp);
		lb.append("url", url);
		lb.append("status", status);
		for (Comment _c : list) {
			CommentImpl c = (CommentImpl)_c;
			lb.append(c.toList());
		}
		lb.append("hash", hash);
		return lb.toList();
	}
	
	@Override
	public String toString() {
		return this.previd + "," + this.status + "," + this.list.toString();
	}
	
	public void add(Comment _c) {
		CommentImpl c = (CommentImpl)_c;
		c.checkArchived();
		c.setPrevid(getLastid());
		c.archive(this);
		list.add(c);
	}
	
	private String getLastid() {
		if (list.size() == 0) {
			return FIRSTID;
		}
		return list.get(list.size() - 1).getId();
	}
	
	public void validate(String previd, String timestamp, String status, String hash) {
		this.previd = previd;
		this.timestamp = timestamp;
		this.status = status;
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
		db.append("timestamp", timestamp);
		db.append("url", url);
		db.append("status", status);
		for (Comment _c : list) {
			CommentImpl c = (CommentImpl)_c;
			db.append("comment", c.getHash());
		}
		return db.toString();
	}
	
	void setPrevid(String previd) {
		this.previd = previd;
		if (this.timestamp.length() == 0) {
			this.timestamp = DateUtil.createTimestampStr();
		}
		this.status = COMPLETE;
		this.hash = toDigestString();
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public String getUrl() {
		return url;
	}

	public String getStatus() {
		return status;
	}

	public List<Comment> getList() {
		return list;
	}

}
