/*
	トピック（Topic）とコメント(Comment）の操作を行うビジネスとロジックを扱うサービス
	本来的には、ストレージへのアクセスはdomainパッケージを利用するべきだが、
	libと機能がかぶるため、このクラスでストレージへのアクセスを行う。
	各コントローラから呼び出される
 */
package gr.unirico.mcfapp.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import gr.unirico.mcflib.api.Comment;
import gr.unirico.mcflib.api.McfApi;
import gr.unirico.mcflib.api.McfApiFactory;
import gr.unirico.mcflib.api.Topic;

/*
	@Serviceアノテーションをつけることで呼び出された際にシングルトンなインスタンスを暗黙的に生成してくれる
 */
@Service
public class TopicService {
	private Logger logger = LoggerFactory.getLogger(TopicService.class);
    private McfApi api = McfApiFactory.getInstance();

	/**
	 * コメント可能なトピック一覧の取得
	 * @return トピックのリスト
	 */
	public List<Topic> getTopicList() {
        return api.getTopicList();
	}

	public Topic getTopic(String topicId) {
	    Topic topic = null;
	    try {
	        topic = api.readTopic(topicId);
        } catch (Exception e) {
	        logger.error("Error in getTopic", e);
        }
        return topic;
    }

	/**
	 * トピックを作成
	 * @param sitename サイト名
	 * @param siteurl サイトurl
	 * @return トピックインスタンス
	 */
	public Topic createTopic(String sitename, String siteurl) {
	    Topic t = null;
	    try{
	        t = api.newTopic(sitename, siteurl);
	        api.writeTopic(t);
        }catch (Exception e){
            logger.error("Error in createTopic", e);
        }
		return t;
	}

	/**
	 * 指定したトピックのコメント一覧の取得
	 * @param tid トピックID
	 * @return コメントのリスト
	 */
	public List<Comment> getCommentList(String tid) {
	    List<Comment> list = null;
	    try {
	        Topic topic = api.readTopic(tid);
	        list = topic.getList();
        } catch(Exception e) {
	        logger.error("Error in getCommentList", e);
        }
	    return list;
	}

	/**
	 * コメントを作成
	 * @param userid ユーザID
	 * @param comment コメント
	 * @return コメントインスタンス
	 */
	public Comment createComment(String userid, String comment) {
		return api.newComment(userid, comment);
	}

	/**
	* トピック情報を取得
	* @param tid トピックID
	* @return トピック情報を格納したmap
	*/
	public Map<String, Object> getTopicData(String tid){
        HashMap<String, Object> map = new HashMap<>();
        Topic topic = getTopic(tid);
        map.put("id", tid);
        map.put("name", topic != null ? topic.getName() : "[topic not found.]");
        map.put("url", topic != null ? topic.getUrl() : "[topic not found.]");
        map.put("comments", topic != null ? topic.getList() : new ArrayList<Comment>());
	    return map;
    }

}
