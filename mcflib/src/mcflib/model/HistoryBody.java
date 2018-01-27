package mcflib.model;

import java.util.List;

import mcflib.util.DigestBuilder;
import mcflib.util.ListBuilder;
import mcflib.util.UniqueIdUtil;

public class HistoryBody {
	private String id;
	private String timestamp;
	private String ipaddr;
	private String userid;
	private String docid;
	private String comment;
	private String appdata;
	private String hash;
	
	public HistoryBody() {
		this.id = UniqueIdUtil.generate();
	}

	public List<String> toList() {
		ListBuilder lb = new ListBuilder();
		lb.append("id", id);
		lb.append("timestamp", timestamp);
		lb.append("ipaddr", ipaddr);
		lb.append("userid", userid);
		lb.append("docid", docid);
		lb.append("comment", comment);
		lb.append("appdata", appdata);
		return lb.toList();
	}
	
	@Override
	public String toString() {
		return this.id;
	}
	
	public void setTimestamp(String timestamp) {
		if (this.hash != null) {
			throw new RuntimeException("completed");
		}
		this.timestamp = timestamp;
	}

	public void setIpaddr(String ipaddr) {
		if (this.hash != null) {
			throw new RuntimeException("completed");
		}
		this.ipaddr = ipaddr;
	}

	public void setUserid(String userid) {
		if (this.hash != null) {
			throw new RuntimeException("completed");
		}
		this.userid = userid;
	}

	public void setDocid(String docid) {
		if (this.hash != null) {
			throw new RuntimeException("completed");
		}
		this.docid = docid;
	}

	public void setComment(String comment) {
		if (this.hash != null) {
			throw new RuntimeException("completed");
		}
		this.comment = comment;
	}

	public void setAppdata(String appdata) {
		if (this.hash != null) {
			throw new RuntimeException("completed");
		}
		this.appdata = appdata;
	}

	public void complete() {
		this.hash = toDigestString();
	}

	private String toDigestString() {
		DigestBuilder db = new DigestBuilder(HistoryBody.class);
		db.append("id", id);
		db.append("timestamp", timestamp);
		db.append("ipaddr", ipaddr);
		db.append("userid", userid);
		db.append("docid", docid);
		db.append("comment", comment);
		db.append("appdata", appdata);
		return db.toString();
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
