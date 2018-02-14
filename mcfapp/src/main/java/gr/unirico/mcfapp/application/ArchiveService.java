/*
	アーカイブ（Archive）の操作を行うビジネスとロジックを扱うサービス
	本来的には、ストレージへのアクセスはdomainパッケージを利用するべきだが、
	libと機能がかぶるため、このクラスでストレージへのアクセスを行う。
	各コントローラから呼び出される
 */
package gr.unirico.mcfapp.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.unirico.mcflib.api.McfApi;
import gr.unirico.mcflib.api.Topic;

/*
	@Serviceアノテーションをつけることで呼び出された際にシングルトンなインスタンスを暗黙的に生成してくれる
 */
@Service
public class ArchiveService {
	//private Logger logger = LoggerFactory.getLogger(ArchiveService.class);

	@Autowired
	McfApi api;

	/**
	 * アーカイブされたトピック一覧の取得
	 * @return トピックのリスト
	 */
	public List<Topic> getArchivedTopicList() {
	    return api.getArchivedTopicList();
	}

	/**
	 * アーカイブされたトピックの取得
	 * @param topicId トピックID
	 * @return トピック
	 */
	public Topic getArchivedTopic(String topicId) throws Exception {
	    for(Topic topic : getArchivedTopicList()){
	    	if(topic.getId().equals(topicId)){
	    		return topic;
			}
		}
		throw new Exception("No such topic " + topicId);
	}

	/**
	 * 指定したトピックをアーカイブ
	 * @param topic トピック
	 */
	public void archiveTopic(Topic topic) throws Exception {
		api.archiveTopic(topic);
	}

}
