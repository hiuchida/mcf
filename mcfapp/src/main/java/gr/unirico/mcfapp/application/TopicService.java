/*
	トピック（Topic）とコメント(Comment）の操作を行うビジネスとロジックを扱うサービス
	本来的には、ストレージへのアクセスはdomainパッケージを利用するべきだが、
	libと機能がかぶるため、このクラスでストレージへのアクセスを行う。
	各コントローラから呼び出される
 */
package gr.unirico.mcfapp.application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.unirico.mcflib.api.McfApi;
import gr.unirico.mcflib.api.Topic;

/*
	@Serviceアノテーションをつけることで呼び出された際にシングルトンなインスタンスを暗黙的に生成してくれる
 */
@Service
public class TopicService {
	//private Logger logger = LoggerFactory.getLogger(TopicService.class);

	@Autowired
	McfApi api;

	/**
	 * コメント可能なトピック一覧の取得
	 * @return トピックのリスト
	 */
	public List<Topic> getTopicList() {
        return api.getTopicList();
	}

	/**
	 * トピックを取得
	 * @param topicId トピックID
	 * @return トピックインスタンス
	 */
	public Topic getTopic(String topicId) throws Exception {
	    return api.readTopic(topicId);
    }

	/**
	 * トピックを作成
	 * @param sitename サイト名
	 * @param siteurl サイトurl
	 * @return トピックインスタンス
	 */
	public Topic createTopic(String sitename, String siteurl) throws Exception {
		Topic t = api.newTopic(sitename, siteurl);
		api.writeTopic(t);
		return t;
	}

	/**
	 * コメントを追加
	 * @param topicId トピックID
	 * @param userid ユーザID
	 * @param comment コメント
	 */
	public void addComment(String topicId, String userid, String comment) throws Exception {
		Topic topic = api.readTopic(topicId);
		topic.add(api.newComment(userid, comment));
		api.writeTopic(topic);
	}

	/**
	* トピック情報をマップに変換
	* @param topic トピック情報
	* @return トピック情報を格納したマップ
	*/
	public Map<String, Object> getMapFromTopic(Topic topic) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", topic.getId());
        map.put("name", topic.getName());
        map.put("url", topic.getUrl());
        map.put("comments", topic.getList());
	    return map;
    }

}
