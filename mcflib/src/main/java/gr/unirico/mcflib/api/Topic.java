package gr.unirico.mcflib.api;

import java.util.List;

public interface Topic extends Node {
	public void add(Comment c);
	public void setUrl(String url);
	public String getStatus();
	public List<Comment> getList();

}
