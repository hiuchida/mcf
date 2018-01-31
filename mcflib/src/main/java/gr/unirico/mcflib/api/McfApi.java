package gr.unirico.mcflib.api;

import java.io.IOException;
import java.util.List;

public interface McfApi {
	public String getDataDir();
	public Comment newComment(String name);
	public Topic newTopic(String name);
	public List<Topic> getTopicList();
	public void writeTopic(Topic hl) throws IOException;
	public Topic readTopic(String id) throws IOException;
	public void archiveTopic(Topic hl) throws IOException;

}
