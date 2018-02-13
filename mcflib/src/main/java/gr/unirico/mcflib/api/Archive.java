package gr.unirico.mcflib.api;

import java.util.List;

public interface Archive extends Node {
	public void add(Topic t);
	public int getListsize();
	public List<Topic> getList();

}
