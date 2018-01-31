package gr.unirico.mcflib.api;

import java.io.IOException;

public interface McfApi {
	public String getDataDir();
	public Comment newComment(String name);
	public Topic newTopic(String name);
	public void writeTopic(Topic hl) throws IOException;
	public Topic readTopic(String id) throws IOException;
	public void archiveTopic(Topic hl) throws IOException;

}
