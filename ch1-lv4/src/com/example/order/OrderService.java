package com.example.order;

/**
 * 受発注業務ロジックを集約するサービスクラス。
 */
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    /**
     * 注文を受付登録する。
     */
    public void accept(Order order) {
        order.setStatus("受付");
        repository.save(order);
    }

    /**
     * 注文を出荷済みに更新する。
     */
    public void ship(String orderId) {
        Order order = repository.findById(orderId);
        if (order == null) {
            throw new InvalidOrderException(orderId, "注文が見つかりません");
        }
        order.setStatus("出荷済");
    }

    /**
     * 全注文の総売上金額を集計する。
     */
    public int calcGrandTotal() {
        int total = 0;
        for (Order order : repository.findAll()) {
            total += order.calcTotal();
        }
        return total;
    }

    /**
     * 全注文の明細を一覧表示する。
     */
    public void printAllItems() {
        System.out.println("===== 注文明細一覧 =====");
        for (Order order : repository.findAll()) {
        	for (OrderItem item : order.getItems()) {

        	    if (item == null) {
        	        continue;
        	    }

        	    System.out.printf(
        	            "[%s] %-24s  数量：%d  単価：%,6d円  小計：%,6d円%n",
        	            order.getOrderId(),
        	            item.getProductName(),
        	            item.getQuantity(),
        	            item.getUnitPrice(),
        	            item.calcSubtotal()
        	    );
        	}
            }
        }
    

    /**
     * 全明細の平均単価を計算する。
     */
    public double calcAverageItemPrice() {

        int sum = 0;
        int count = 0;

        for (Order order : repository.findAll()) {
            for (OrderItem item : order.getItems()) {

                if (item == null) {
                    continue;
                }

                sum += item.getUnitPrice();
                count++;
            }
        }

        return (double) sum / count;
    }
    
    /**
     * 登録件数を返す。
     */
    public int getOrderCount() {
        return repository.count();
    }
}