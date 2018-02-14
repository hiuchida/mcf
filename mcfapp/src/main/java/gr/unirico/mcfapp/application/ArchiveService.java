/*
	アーカイブ（Archive）の操作を行うビジネスとロジックを扱うサービス
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
public class ArchiveService {

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
	public void archiveTopic(Topic t) throws Exception{
		api.archiveTopic(t);
	}

	/**
	 * アーカイブ済みのトピックの情報を取得
	 * @param aid アーカイブ済みトピックID
	 * @return トピック情報を格納したmap
	 */
	public Map<String, Object> getArchivedTopicData(String aid) throws Exception{
		Map<String, Object> map = new HashMap<>();
		Topic topic = getArchivedTopic(aid);
		map.put("id", aid);
		map.put("name", topic.getName());
        map.put("url", topic.getUrl());
		map.put("comments", topic.getList());
		return map;
	}

}
