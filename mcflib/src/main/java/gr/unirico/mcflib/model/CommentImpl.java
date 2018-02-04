package gr.unirico.mcflib.model;

import java.util.List;

import gr.unirico.mcflib.api.Comment;
import gr.unirico.mcflib.exception.IllegalPrevidException;
import gr.unirico.mcflib.util.DateUtil;
import gr.unirico.mcflib.util.DigestBuilder;
import gr.unirico.mcflib.util.ListBuilder;

public class CommentImpl extends NodeImpl implements Comment {
	private String userid = "";
	private String comment = "";

	public CommentImpl(String name) {
		super(name);
	}

	public CommentImpl(String previd, String id, String name, String timestamp) {
		super(previd, id, name, timestamp);
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
