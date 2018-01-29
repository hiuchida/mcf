package mcflib.model;

public abstract class Node {
	public static final String FIRSTID = "first-id";

	private boolean bArchived;
	protected String previd;
	protected String hash;
	
	public Node() {
		this.previd = FIRSTID;
	}
	
	protected void archive() {
		bArchived = true;
	}
	
	protected void checkArchived() {
		if (bArchived) {
			throw new RuntimeException("archived");
		}
	}

	public String getPrevid() {
		return previd;
	}

	public String getHash() {
		return hash;
	}

}
