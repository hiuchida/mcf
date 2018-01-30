package gr.unirico.mcflib.model;

public abstract class Node {
	public static final String FIRSTID = "first-id";

	private boolean bArchived;
	private Node parent;
	protected String previd;
	protected String id;
	protected String name;
	protected String hash;
	
	public Node(String id, String name) {
		this.previd = FIRSTID;
		this.id = id;
		this.name = name;
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

	public String getName() {
		return name;
	}

	public String getHash() {
		return hash;
	}

}
