package gr.unirico.mcflib.model;

import java.util.List;

import gr.unirico.mcflib.util.DigestBuilder;
import gr.unirico.mcflib.util.ListBuilder;
import gr.unirico.mcflib.util.UniqueIdUtil;

public class History extends Node {
	private String timestamp;
	private String ipaddr;
	private String userid;
	private String docid;
	private String comment;
	private String appdata;

	public History() {
		this(UniqueIdUtil.generate());
	}

	public History(String id) {
		super(id);
	}

	public List<String> toList() {
		this.hash = toDigestString();
		ListBuilder lb = new ListBuilder(History.class);
		lb.append("previd", previd);
		lb.append("id", id);
		lb.append("timestamp", timestamp);
		lb.append("ipaddr", ipaddr);
		lb.append("userid", userid);
		lb.append("docid", docid);
		lb.append("comment", comment);
		lb.append("appdata", appdata);
		lb.append("hash", hash);
		return lb.toList();
	}
	
	@Override
	public String toString() {
		return "(" + this.id + "," + this.previd + ")";
	}
	
	public void validate(String previd, String hash) {
		this.previd = previd;
		this.hash = toDigestString();
		if (!this.hash.equals(hash)) {
			throw new RuntimeException("Illegal hash");
		}
	}

	private String toDigestString() {
		DigestBuilder db = new DigestBuilder(History.class);
		db.append("previd", previd);
		db.append("id", id);
		db.append("timestamp", timestamp);
		db.append("ipaddr", ipaddr);
		db.append("userid", userid);
		db.append("docid", docid);
		db.append("comment", comment);
		db.append("appdata", appdata);
		return db.toString();
	}
	
	void setPrevid(String previd) {
		this.previd = previd;
		this.hash = toDigestString();
	}

	public void setTimestamp(String timestamp) {
		checkArchived();
		this.timestamp = timestamp;
	}

	public void setIpaddr(String ipaddr) {
		checkArchived();
		this.ipaddr = ipaddr;
	}

	public void setUserid(String userid) {
		checkArchived();
		this.userid = userid;
	}

	public void setDocid(String docid) {
		checkArchived();
		this.docid = docid;
	}

	public void setComment(String comment) {
		checkArchived();
		this.comment = comment;
	}

	public void setAppdata(String appdata) {
		checkArchived();
		this.appdata = appdata;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public String getIpaddr() {
		return ipaddr;
	}

	public String getUserid() {
		return userid;
	}

	public String getDocid() {
		return docid;
	}

	public String getComment() {
		return comment;
	}

	public String getAppdata() {
		return appdata;
	}

}
