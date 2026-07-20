# 機能ID：F-101
# 機能名：注文確定ボタン押下処理（修正版）

## 処理フロー表

| No. | 処理内容 | 担当 | 条件分岐・例外 | 対象DOM要素 |
|------|----------|------|----------------|-------------|
| 1 | `#confirmBtn` のclickイベントを検出する | addEventListener | ― | `#confirmBtn` |
| 1.5 | 「本当に注文を確定しますか？」という確認ダイアログを表示する | `window.confirm()` | キャンセル（false）の場合：処理終了 | ― |
| 2 | `#errorMsg` を非表示にし、`#loadingMsg` を表示する（初期化） | DOM操作 | ― | `#errorMsg`、`#loadingMsg` |
| 3 | `#confirmBtn` をdisabledにする（二重送信防止） | DOM操作 | ― | `#confirmBtn` |
| 4 | `POST /api/orders` にfetchリクエストを送信する | fetch / await | ― | ― |
| 5 | `response.ok` を確認する | response.ok | falseの場合：エラーをthrow → No.7へ | ― |
| 6 | レスポンスJSONから `order_id` を取得し、`#successModal` を表示する。`#countdown` に「5秒後に注文確認ページへ移動します...」を表示し、`setInterval()` を使って1秒ごとにカウントダウンを更新する。0秒になったら `location.href="/orders/{order_id}"` へ自動遷移する。 | response.json() / DOM操作 / setInterval / location.href | タイマー終了後に注文確認画面へ遷移 | `#successModal`、`#orderIdText`、`#countdown` |
| 7 | 【catch】レスポンスの `errorType` に応じてエラーメッセージを切り替え、`#errorMsg` に表示する | catch / DOM操作 | EMPTY_CART：カートが空です／INSUFFICIENT_STOCK：レスポンスのmessage／500：サーバーエラー | `#errorMsg` |
| 8 | 【finally】`#loadingMsg` を非表示・`#confirmBtn` のdisabledを解除する | finally / DOM操作 | ― | `#loadingMsg`、`#confirmBtn` |

---

## 例外処理方針表

| 例外の種類 | 表示内容 | 対象DOM |
|------------|----------|----------|
| 400・errorType: EMPTY_CART | カートが空です。商品を追加してから注文してください。 | `#errorMsg` |
| 400・errorType: INSUFFICIENT_STOCK | レスポンスのmessageをそのまま表示する | `#errorMsg` |
| 500 Internal Server Error | サーバーエラーが発生しました。しばらく経ってから再試行してください。 | `#errorMsg` |
| ネットワークエラー | 通信エラーが発生しました。インターネット接続を確認してください。 | `#errorMsg` |
| `order_id` が取得できない | 自動遷移を中止し、「注文確認画面へ移動できません。」を表示する | `#errorMsg` |
| `#countdown` が存在しない | カウントダウン表示は行わず、自動遷移のみ実施する | `#countdown` |

---

## 追加するDOM要素

| DOM要素 | 用途 |
|---------|------|
| `#confirmBtn` | 注文確定ボタン |
| `#loadingMsg` | ローディング表示 |
| `#errorMsg` | エラーメッセージ表示 |
| `#successModal` | 注文成功モーダル |
| `#orderIdText` | 注文ID表示 |
| `#countdown` | 「5秒後に注文確認ページへ移動します...」および残り秒数を表示する |

---

## 変更内容

### 変更①
- 処理フロー No.6 を更新
- 成功モーダル表示後に5秒間のカウントダウンを表示
- `setInterval()` により1秒ごとに残り秒数を更新
- カウントダウン終了後、`location.href="/orders/{order_id}"` に自動遷移

### 変更②
- 「対象DOM要素」列を追加
- `#countdown` を新規追加

### 変更③
- 例外処理方針表に以下を追加
  - `order_id` が取得できない場合の対応
  - `#countdown` が存在しない場合の対応