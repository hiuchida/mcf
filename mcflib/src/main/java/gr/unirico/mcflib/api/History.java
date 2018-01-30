package gr.unirico.mcflib.api;

public interface History extends Node {
	public void setTimestamp(String timestamp);
	public void setIpaddr(String ipaddr);
	public void setUserid(String userid);
	public void setDocid(String docid);
	public void setComment(String comment);
	public void setAppdata(String appdata);
	public String getTimestamp();
	public String getIpaddr();
	public String getUserid();
	public String getDocid();
	public String getComment();
	public String getAppdata();

}
