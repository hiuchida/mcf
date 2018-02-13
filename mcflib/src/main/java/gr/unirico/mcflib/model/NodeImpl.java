package gr.unirico.mcflib.model;

import gr.unirico.mcflib.api.Node;
import gr.unirico.mcflib.exception.IllegalHashException;
import gr.unirico.mcflib.exception.IllegalPrevidException;
import gr.unirico.mcflib.exception.IllegalProofException;
import gr.unirico.mcflib.impl.ProofOfWork;
import gr.unirico.mcflib.util.DateUtil;
import gr.unirico.mcflib.util.DigestBuilder;
import gr.unirico.mcflib.util.ListBuilder;
import gr.unirico.mcflib.util.StringUtil;
import gr.unirico.mcflib.util.UniqueIdUtil;

public abstract class NodeImpl implements Node {
	protected static final String FIRSTID = "first-id";
	protected static final int FIRSTPROOF = 0;
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
	protected int proof;
	protected String validateproof;
	protected String hash;
	
	public NodeImpl(String name) {
		this.previd = FIRSTID;
		this.prevhash = FIRSTHASH;
		this.id = UniqueIdUtil.generate();
		this.name = name;
		this.timestamp = DateUtil.createTimestampStr();
		this.status = EDITING;
		this.proof = FIRSTPROOF;
		this.validateproof = "" + FIRSTPROOF;
	}
	
	public NodeImpl(String previd, String prevhash, String id, String name, String timestamp, String status, int proof) {
		this.previd = previd;
		this.prevhash = prevhash;
		this.id = id;
		this.name = name;
		this.timestamp = timestamp;
		this.status = status;
		this.proof = proof;
		this.validateproof = "" + proof;
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
		lb.append("proof", proof);
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
		db.append("proof", proof);
		return db;
	}

	protected synchronized void archive(boolean bValidate, NodeImpl parent, String previd, String prevhash, int prevproof) {
		checkArchived();
		if (bValidate) {
			if (!previd.equals(this.previd)) {
				throw new IllegalPrevidException(previd + "," + this.previd);
			}
			if (!prevhash.equals(this.prevhash)) {
				throw new IllegalHashException(prevhash + "," + this.prevhash);
			}
			if (!ProofOfWork.validate(prevproof, prevhash, this.proof)) {
				throw new IllegalProofException(prevproof + "," + this.proof);
			}
		} else {
			this.previd = previd;
			this.prevhash = prevhash;
			this.timestamp = DateUtil.createTimestampStr();
			this.proof = ProofOfWork.calc(prevproof, prevhash);
		}
		this.validateproof = ProofOfWork.calcHash(prevproof, prevhash, this.proof);
		setArchived(this);
	}

	protected synchronized void setArchived(NodeImpl parent) {
		this.bArchived = true;
		this.parent = parent;
		this.status = FIXED;
		this.hash = toDigestString();
	}
	
	protected synchronized void checkArchived() {
		if (bArchived) {
			throw new IllegalStateException("Already archived");
		}
	}

	public void validate(String hash) {
		this.hash = toDigestString();
		if (!hash.equals(this.hash)) {
			throw new IllegalHashException(hash + "," + this.hash);
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

	public String getShortprevhash() {
		return StringUtil.getShortHash(prevhash);
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

	public int getProof() {
		return proof;
	}

	public String getValidateproof() {
		return validateproof;
	}
	
	public String getHash() {
		return hash;
	}

	public String getShorthash() {
		return StringUtil.getShortHash(hash);
	}

}
