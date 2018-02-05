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

    private McfApi api = McfApiFactory.getInstance();
	/*
		必須メソッド(public メソッド)
			* トピック一覧の取得
			* トピックの作成
			* コメント一覧の取得
				** トピックIDで取得
				** 降順ソート
			* コメントの作成
		メソッド自体は特別なアノテーションを必要としない単一のメソッドとして実装すれば良い。
	 */

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
	        e.printStackTrace();
        }
        return topic;
    }

	/**
	 * トピックを作成
	 * @return トピック
	 */
	public Topic createTopic() {
		return api.newTopic("newTopic");
	}

	/**
	 * 指定したトピックのコメント一覧の取得
	 * @param tid トピックID
	 * @param bAsc true:昇順、false:降順
	 * @return コメントのリスト
	 */
	public List<Comment> getCommentList(String tid, boolean bAsc) {

	    List<Comment> list = null;

	    try {
	        Topic topic = api.readTopic(tid);
	        list = topic.getList();
	        list.sort((c1, c2) -> c1.getTimestamp().compareTo(c2.getTimestamp()) * (bAsc ? 1 : -1));
        } catch(Exception e) {
	        e.printStackTrace();
        }
	    return list;
	}

	/**
	 * コメントを作成
	 * @return コメント
	 */
	public Comment createComment() {
		return api.newComment("newComment");
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
        map.put("name", topic != null ? topic.getUrl() : "[topic not found.]");
        map.put("comments", topic != null ? topic.getList() : new ArrayList<Comment>());

	    return map;
    }

}
