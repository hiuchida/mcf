package gr.unirico.mcflib.model;

import java.util.List;

import gr.unirico.mcflib.api.Comment;
import gr.unirico.mcflib.util.DateUtil;
import gr.unirico.mcflib.util.DigestBuilder;
import gr.unirico.mcflib.util.ListBuilder;
import gr.unirico.mcflib.util.UniqueIdUtil;

public class CommentImpl extends NodeImpl implements Comment {
	private String timestamp = "";
	private String userid = "";
	private String comment = "";

	public CommentImpl(String name) {
		this(UniqueIdUtil.generate(), name);
	}

	public CommentImpl(String id, String name) {
		super(id, name);
	}

	public synchronized List<String> toList() {
		this.hash = toDigestString();
		ListBuilder lb = new ListBuilder(CommentImpl.class);
		lb.append("previd", previd);
		lb.append("id", id);
		lb.append("name", name);
		lb.append("timestamp", timestamp);
		lb.append("userid", userid);
		lb.append("comment", comment);
		lb.append("hash", hash);
		return lb.toList();
	}
	
	@Override
	public String toString() {
		return "(" + this.id + "," + this.previd + ")";
	}
	
	public synchronized void validate(String previd, String timestamp, String hash) {
		this.previd = previd;
		this.timestamp = timestamp;
		this.hash = toDigestString();
		if (!this.hash.equals(hash)) {
			throw new RuntimeException("Illegal hash");
		}
	}

	private synchronized String toDigestString() {
		DigestBuilder db = new DigestBuilder(CommentImpl.class);
		db.append("previd", previd);
		db.append("id", id);
		db.append("name", name);
		db.append("timestamp", timestamp);
		db.append("userid", userid);
		db.append("comment", comment);
		return db.toString();
	}
	
	synchronized void setPrevid(String previd) {
		this.previd = previd;
		if (this.timestamp.length() == 0) {
			this.timestamp = DateUtil.createTimestampStr();
		}
		this.hash = toDigestString();
	}

	public synchronized void setUserid(String userid) {
		checkArchived();
		this.userid = userid;
	}

	public synchronized void setComment(String comment) {
		checkArchived();
		this.comment = comment;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public String getUserid() {
		return userid;
	}

	public String getComment() {
		return comment;
	}

}
