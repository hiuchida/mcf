package mcflib.model;

import java.util.List;

import mcflib.util.DigestBuilder;
import mcflib.util.ListBuilder;
import mcflib.util.UniqueIdUtil;

public class History extends Node {
	private String previd;
	private String id;
	private String timestamp;
	private String ipaddr;
	private String userid;
	private String docid;
	private String comment;
	private String appdata;
	private String hash;

	public History() {
		this.id = UniqueIdUtil.generate();
	}

	public History(String id) {
		this.id = id;
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
		if (this.previd != null) {
			throw new RuntimeException("completed");
		}
		this.timestamp = timestamp;
	}

	public void setIpaddr(String ipaddr) {
		if (this.previd != null) {
			throw new RuntimeException("completed");
		}
		this.ipaddr = ipaddr;
	}

	public void setUserid(String userid) {
		if (this.previd != null) {
			throw new RuntimeException("completed");
		}
		this.userid = userid;
	}

	public void setDocid(String docid) {
		if (this.previd != null) {
			throw new RuntimeException("completed");
		}
		this.docid = docid;
	}

	public void setComment(String comment) {
		if (this.previd != null) {
			throw new RuntimeException("completed");
		}
		this.comment = comment;
	}

	public void setAppdata(String appdata) {
		if (this.previd != null) {
			throw new RuntimeException("completed");
		}
		this.appdata = appdata;
	}

	public String getPrevid() {
		return previd;
	}

	public String getId() {
		return id;
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

	public String getHash() {
		return hash;
	}

}
