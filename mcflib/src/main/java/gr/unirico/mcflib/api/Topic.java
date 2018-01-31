package gr.unirico.mcflib.api;

import java.util.List;

public interface Topic extends Node {
	public void add(Comment h);
	public String getStatus();
	public List<Comment> getList();

}
