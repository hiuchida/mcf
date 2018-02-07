package gr.unirico.mcflib.api;

public interface Node {
	public Node getParent();
	public String getPrevid();
	public String getId();
	public String getName();
	public String getTimestamp();
	public String getStatus();
	public String getHash();

}
