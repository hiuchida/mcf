package gr.unirico.mcflib.api;

import java.io.IOException;

public interface McfApi {
	public String getDataDir();
	public History newHistory(String name);
	public HistoryList newHistoryList(String name);
	public void write(HistoryList hl) throws IOException;
	public HistoryList read(String id) throws IOException;
	public void delete(HistoryList hl);
	public void archive(HistoryList hl) throws IOException;

}
