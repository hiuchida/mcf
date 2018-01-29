package mcflib.model;

public abstract class Node {
	public static final String FIRSTID = "first-id";

	private boolean bArchived;
	
	protected void archive() {
		bArchived = true;
	}
	
	protected void checkArchived() {
		if (bArchived) {
			throw new RuntimeException("archived");
		}
	}
}
