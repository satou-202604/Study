# Ch.8 課題1：REST API仕様書の作成
## 顧客注文履歴システム REST API仕様書

### API一覧


| No | エンドポイント                         | HTTP | 説明                 | リクエストパラメータ | リクエストボディ                                                            | レスポンスボディ                    | ステータスコード     |
|----|----------------------------------------|------|----------------------|----------------------|-----------------------------------------------------------------------------|-------------------------------------|----------------------|
| 1  | `/api/customers`                       | POST | 顧客登録             | なし                 | customerId(String)、customerName(String)、email(String)                    | 登録した顧客情報                    | 201、400、500        |
| 2  | `/api/customers/{customerId}`          | GET  | 顧客取得             | customerId(String)   | なし                                                                        | customerId、customerName、email     | 200、404、500        |
| 3  | `/api/orders`                          | POST | 注文登録             | なし                 | orderId(String)、customerId(String)、productName(String)、amount(int)       | 登録した注文情報                    | 201、400、404、500   |
| 4  | `/api/customers/{customerId}/orders`   | GET  | 注文履歴取得         | customerId(String)   | なし                                                                        | 注文一覧（JSON配列）                | 200、404、500        |
| 5  | `/api/customers/{customerId}/orders/total` | GET  | 顧客の注文合計金額取得 | customerId(String)   | なし                                                                        | customerId、totalAmount(int)        | 200、404、500        |

