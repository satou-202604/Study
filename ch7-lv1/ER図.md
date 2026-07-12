# 顧客注文履歴システム ER図

## ER図

以下は作成したER図です。

![ER図](ER図.png)

---

## テーブル

- customers
- orders
- order_details
- products
- payments

---

## リレーションシップ

- customers 1 : N orders
- orders 1 : N order_details
- products 1 : N order_details
- orders 1 : 1 payments