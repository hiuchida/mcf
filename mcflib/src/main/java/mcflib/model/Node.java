package mcflib.model;

public abstract class Node {
	public static final String FIRSTID = "first-id";

	private boolean bArchived;
	private Node parent;
	protected String previd;
	protected String id;
	protected String hash;
	
	public Node(String id) {
		this.previd = FIRSTID;
		this.id = id;
	}
	
	protected void archive(Node parent) {
		this.bArchived = true;
		this.parent = parent;
	}
	
	protected void checkArchived() {
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

	public String getHash() {
		return hash;
	}

}
