package gr.unirico.mcflib.api;

import java.io.IOException;
import java.util.List;

public interface McfApi {
	public String getDataDir();
	public Comment newComment(String userid, String comment);
	public Topic newTopic(String sitename, String siteurl);
	public List<Topic> getTopicList();
	public List<Topic> getArchivedTopicList();
	public void writeTopic(Topic t) throws IOException;
	public Topic readTopic(String id) throws IOException;
	public void archiveTopic(Topic t) throws IOException;

}
