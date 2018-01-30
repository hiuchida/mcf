package gr.unirico.mcflib.api;

import java.util.List;

public interface HistoryList extends Node {
	public void add(History h);
	public String getStatus();
	public List<History> getList();

}
