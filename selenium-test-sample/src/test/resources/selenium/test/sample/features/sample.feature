#language: ja

フィーチャ: Cucumberのサンプルテスト
	シナリオ: メッセージを表示する
		前提 ページを表示する
 		もし "World"を入力した
 		かつ サブミットした
		ならば "Hello World!"と表示されていること
		
		前提 ページを表示する
 		もし "Test"を入力した
 		かつ サブミットした
		ならば "Hello Test!"と表示されていること