package gr.unirico.mcflib.api;

public interface Comment extends Node {
	public void setUserid(String userid);
	public void setComment(String comment);
	public String getUserid();
	public String getComment();

}
