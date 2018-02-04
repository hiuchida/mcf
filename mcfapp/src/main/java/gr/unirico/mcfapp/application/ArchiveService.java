/*
	アーカイブ（Archive）の操作を行うビジネスとロジックを扱うサービス
	本来的には、ストレージへのアクセスはdomainパッケージを利用するべきだが、
	libと機能がかぶるため、このクラスでストレージへのアクセスを行う。
	各コントローラから呼び出される
 */

package gr.unirico.mcfapp.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gr.unirico.mcflib.api.Comment;
import gr.unirico.mcflib.api.McfApi;
import gr.unirico.mcflib.api.McfApiFactory;
import gr.unirico.mcflib.impl.McfApiImpl;
import org.springframework.stereotype.Service;

import gr.unirico.mcflib.api.Topic;

@Service
/*
	@Serviceアノテーションをつけることで呼び出された際にシングルトンなインスタンスを暗黙的に生成してくれる
 */
public class ArchiveService {

    private McfApiImpl api = (McfApiImpl) McfApiFactory.getInstance();

	/*
		必須メソッド(public メソッド)
			* アーカイブ一覧の取得
			* 特定のアーカイブの取得
				** アーカイブIDで取得
			* アーカイブの作成
		メソッド自体は特別なアノテーションを必要としない単一のメソッドとして実装すれば良い。
	 */
	
	/**
	 * アーカイブされたトピック一覧の取得
	 * @return トピックのリスト
	 */
	public List<Topic> getArchivedTopicList() {
	    return api.getArchivedTopicList();
	}

	/**
	 * アーカイブされたトピックの取得
	 * @param tid トピックID
	 * @return トピック
	 */
	public Topic getArchivedTopic(String tid) {
	    for(Topic t : getArchivedTopicList()){
	    	if(t.getId().equals(tid)){
	    		return t;
			}
		}
		return null;
	}

	/**
	 * 指定したトピックをアーカイブ
	 * @param t トピック
	 */
	public void archiveTopic(Topic t) {
	    try{
            api.archiveTopic(t);
        } catch (Exception e) {
	        e.printStackTrace();
        }
	}

	/**
	 * アーカイブ済みのトピックの情報を取得
	 * @param aid アーカイブ済みトピックID
	 * @return トピック情報を格納したmap
	 */
	public Map<String, Object> getArchivedTopicData(String aid) {
		Map<String, Object> map = new HashMap<>();

		Topic topic = getArchivedTopic(aid);
		map.put("id", aid);
		map.put("name", topic != null ? topic.getName() : "[topic not found.]");
		map.put("comments", topic != null ? topic.getList() : new ArrayList<Comment>());

		return map;
	}

}
