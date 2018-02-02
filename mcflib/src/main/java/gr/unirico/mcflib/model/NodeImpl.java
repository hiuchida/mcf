package gr.unirico.mcflib.model;

import gr.unirico.mcflib.api.Node;

public abstract class NodeImpl implements Node {
	public static final String FIRSTID = "first-id";

	private boolean bArchived;
	private NodeImpl parent;
	protected String previd;
	protected String id;
	protected String name;
	protected String hash;
	
	public NodeImpl(String id, String name) {
		this.previd = FIRSTID;
		this.id = id;
		this.name = name;
	}
	
	protected synchronized void archive(NodeImpl parent) {
		this.bArchived = true;
		this.parent = parent;
	}
	
	protected synchronized void checkArchived() {
		if (bArchived) {
			throw new RuntimeException("archived");
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
