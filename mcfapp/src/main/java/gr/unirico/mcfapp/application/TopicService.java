/*
	トピック（Topic）とコメント(Comment）の操作を行うビジネスとロジックを扱うサービス
	本来的には、ストレージへのアクセスはdomainパッケージを利用するべきだが、
	libと機能がかぶるため、このクラスでストレージへのアクセスを行う。
	各コントローラから呼び出される
 */

package gr.unirico.mcfapp.application;

import java.util.List;

import org.springframework.stereotype.Service;

import gr.unirico.mcflib.api.Comment;
import gr.unirico.mcflib.api.Topic;

@Service
/*
	@Serviceアノテーションをつけることで呼び出された際にシングルトンなインスタンスを暗黙的に生成してくれる
 */
public class TopicService {

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
		return null;
	}

	/**
	 * トピックを作成
	 * @return トピック
	 */
	public Topic createTopic() {
		return null;
	}

	/**
	 * 指定したトピックのコメント一覧の取得
	 * @param tid トピックID
	 * @param bAsc true:昇順、false:降順
	 * @return コメントのリスト
	 */
	public List<Comment> getCommentList(String tid, boolean bAsc) {
		return null;
	}

	/**
	 * コメントを作成
	 * @return コメント
	 */
	public Comment createComment() {
		return null;
	}

}
