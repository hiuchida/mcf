/*
	トピック（HistoryList）とコメント(History）の操作を行うビジネスとロジックを扱うサービス
	本来的には、ストレージへのアクセスはdomainパッケージを利用するべきだが、
	libと機能がかぶるため、このクラスでストレージへのアクセスを行う。
	各コントローラから呼び出される
 */

package gr.unirico.mcfapp.application;

import org.springframework.stereotype.Service;

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

}
