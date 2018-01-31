/*
	アーカイブ（Archive）の操作を行うビジネスとロジックを扱うサービス
	本来的には、ストレージへのアクセスはdomainパッケージを利用するべきだが、
	libと機能がかぶるため、このクラスでストレージへのアクセスを行う。
	各コントローラから呼び出される
 */

package gr.unirico.mcfapp.application;

import java.util.List;

import org.springframework.stereotype.Service;

import gr.unirico.mcflib.api.Topic;

@Service
/*
	@Serviceアノテーションをつけることで呼び出された際にシングルトンなインスタンスを暗黙的に生成してくれる
 */
public class ArchiveService {

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
		return null;
	}

	/**
	 * アーカイブされたトピックの取得
	 * @param tid トピックID
	 * @return トピック
	 */
	public Topic getArchivedTopic(String tid) {
		return null;
	}

	/**
	 * 指定したトピックをアーカイブ
	 * @param t トピック
	 */
	public void archiveTopic(Topic t) {
	}

}
