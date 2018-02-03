package gr.unirico.mcflib.model;

import gr.unirico.mcflib.api.Node;
import gr.unirico.mcflib.util.DigestBuilder;
import gr.unirico.mcflib.util.ListBuilder;
import gr.unirico.mcflib.util.UniqueIdUtil;

public abstract class NodeImpl implements Node {
	public static final String FIRSTID = "first-id";

	private boolean bArchived;
	private NodeImpl parent;
	protected String previd;
	protected String id;
	protected String name;
	protected String timestamp;
	protected String hash;
	
	public NodeImpl(String name) {
		this.previd = FIRSTID;
		this.id = UniqueIdUtil.generate();
		this.name = name;
		this.timestamp = "";
	}
	
	public NodeImpl(String previd, String id, String name, String timestamp) {
		this.previd = previd;
		this.id = id;
		this.name = name;
		this.timestamp = timestamp;
	}
	
	protected abstract String toDigestString();

	protected ListBuilder newListBuilder(Class<?> klass) {
		this.hash = toDigestString();
		ListBuilder lb = new ListBuilder(klass);
		lb.append("previd", previd);
		lb.append("id", id);
		lb.append("name", name);
		lb.append("timestamp", timestamp);
		return lb;
	}

	protected DigestBuilder newDigestBuilder(Class<?> klass) {
		DigestBuilder db = new DigestBuilder(klass);
		db.append("previd", previd);
		db.append("id", id);
		db.append("name", name);
		db.append("timestamp", timestamp);
		return db;
	}
	protected synchronized void setArchived(NodeImpl parent) {
		this.bArchived = true;
		this.parent = parent;
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
			throw new RuntimeException("Illegal hash");
		}
	}

	public Node getParent() {
		return parent;
	}

	public String getPrevid() {
		return previd;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getHash() {
		return hash;
	}

}
