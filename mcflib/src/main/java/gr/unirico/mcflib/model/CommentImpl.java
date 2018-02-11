package gr.unirico.mcflib.model;

import java.util.List;

import gr.unirico.mcflib.api.Comment;
import gr.unirico.mcflib.util.DigestBuilder;
import gr.unirico.mcflib.util.ListBuilder;

public class CommentImpl extends NodeImpl implements Comment {
	private String userid = "";
	private String comment = "";

	public CommentImpl(String name) {
		super(name);
	}

	public CommentImpl(String previd, String prevhash, String id, String name, String timestamp, String status, int proof) {
		super(previd, prevhash, id, name, timestamp, status, proof);
	}

	public List<String> toList() {
		ListBuilder lb = newListBuilder(CommentImpl.class);
		lb.append("userid", userid);
		lb.append("comment", comment);
		lb.append("hash", hash);
		return lb.toList();
	}
	
	@Override
	protected String toDigestString() {
		DigestBuilder db = newDigestBuilder(CommentImpl.class);
		db.append("userid", userid);
		db.append("comment", comment);
		return db.toString();
	}
	
	@Override
	public String toString() {
		return "(" + this.id + "," + this.previd + ")";
	}
	
	public synchronized void setUserid(String userid) {
		checkArchived();
		this.userid = userid;
	}

	public synchronized void setComment(String comment) {
		checkArchived();
		this.comment = comment;
	}

	public String getUserid() {
		return userid;
	}

	public String getComment() {
		return comment;
	}

}
