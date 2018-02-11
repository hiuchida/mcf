package gr.unirico.mcflib.api;

public interface Node {
	public Node getParent();
	public String getPrevid();
	public String getPrevhash();
	public String getId();
	public String getName();
	public String getTimestamp();
	public String getStatus();
	public int getProof();
	public String getHash();

}
