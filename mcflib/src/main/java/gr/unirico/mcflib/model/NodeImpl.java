package gr.unirico.mcflib.model;

import gr.unirico.mcflib.api.Node;
import gr.unirico.mcflib.exception.IllegalHashException;
import gr.unirico.mcflib.util.DateUtil;
import gr.unirico.mcflib.util.DigestBuilder;
import gr.unirico.mcflib.util.ListBuilder;
import gr.unirico.mcflib.util.UniqueIdUtil;

public abstract class NodeImpl implements Node {
	protected static final String FIRSTID = "first-id";
	protected static final String FIRSTHASH = "0";
	private static final String EDITING = "editing";
	private static final String FIXED = "fixed";

	private boolean bArchived;
	private NodeImpl parent;
	protected String previd;
	protected String prevhash;
	protected String id;
	protected String name;
	protected String timestamp;
	protected String status;
	protected String hash;
	
	public NodeImpl(String name) {
		this.previd = FIRSTID;
		this.prevhash = FIRSTHASH;
		this.id = UniqueIdUtil.generate();
		this.name = name;
		this.timestamp = DateUtil.createTimestampStr();
		this.status = EDITING;
	}
	
	public NodeImpl(String previd, String prevhash, String id, String name, String timestamp, String status) {
		this.previd = previd;
		this.prevhash = prevhash;
		this.id = id;
		this.name = name;
		this.timestamp = timestamp;
		this.status = status;
	}
	
	protected abstract String toDigestString();

	protected ListBuilder newListBuilder(Class<?> klass) {
		this.hash = toDigestString();
		ListBuilder lb = new ListBuilder(klass);
		lb.append("previd", previd);
		lb.append("prevhash", prevhash);
		lb.append("id", id);
		lb.append("name", name);
		lb.append("timestamp", timestamp);
		lb.append("status", status);
		return lb;
	}

	protected DigestBuilder newDigestBuilder(Class<?> klass) {
		DigestBuilder db = new DigestBuilder(klass);
		db.append("previd", previd);
		db.append("prevhash", prevhash);
		db.append("id", id);
		db.append("name", name);
		db.append("timestamp", timestamp);
		db.append("status", status);
		return db;
	}
	protected synchronized void setArchived(NodeImpl parent) {
		this.bArchived = true;
		this.parent = parent;
		this.status = FIXED;
		this.hash = toDigestString();
	}
	
	protected synchronized void checkArchived() {
		if (bArchived) {
			throw new RuntimeException("archived");
		}
	}

	public void validate(String hash) {
		this.hash = toDigestString();
		if (!this.hash.equals(hash)) {
			throw new IllegalHashException(this.hash);
		}
	}

	public Node getParent() {
		return parent;
	}

	public String getPrevid() {
		return previd;
	}

	public String getPrevhash() {
		return prevhash;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public String getStatus() {
		return status;
	}

	public String getHash() {
		return hash;
	}

}
