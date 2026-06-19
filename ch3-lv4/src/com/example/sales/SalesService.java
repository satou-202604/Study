package com.example.sales;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 売上集計サービスクラス。
 */
public class SalesService {

    private final SalesRepository repository;

    public SalesService(SalesRepository repository) {
        this.repository = repository;
    }

    /**
     * 売上レコードを登録する。
     *
     * @param record 登録する売上レコード
     */
    public void register(SalesRecord record) {
        repository.add(record);
    }

    /**
     * 担当者別売上合計を返す。
     *
     * @return 担当者名 → 売上合計
     */
    public Map<String, Integer> aggregateByRep() {

        Map<String, Integer> result = new HashMap<>();

        for (SalesRecord r : repository.findAll()) {

            String repName =
                (r.getSalesRepName() == null)
                    ? "未分類"
                    : r.getSalesRepName();

            result.put(
                repName,
                result.getOrDefault(repName, 0)
                    + r.calcAmount()
            );
        }

        return result;
    }

    /**
     * 週次売上レポートを印字する。
     */
    public void printWeeklySalesReport() {

        List<SalesRecord> all = repository.findAll();

        System.out.println("========== 週次売上レポート ==========");

        System.out.printf(
        	    "%-8s %-10s %-10s %-10s %12s %8s%n",
        	    "売上ID",
        	    "商品ID",
        	    "担当者",
        	    "カテゴリ",
        	    "金額（円）",
        	    "数量"
        	);

        System.out.println(
            "----------------------------------------------------------------------------"
        );

        for (SalesRecord r : all) {

            String repName =
                (r.getSalesRepName() == null)
                    ? "未分類"
                    : r.getSalesRepName();

            String category =
                (r.getCategory() == null)
                    ? "未分類"
                    : r.getCategory();

            System.out.printf(
            	    "%-8s %-10s %-10s %-10s %,12d円 %8s%n",
            	    r.getSalesId(),
            	    r.getProductId(),
            	    repName,
            	    category,
            	    r.calcAmount(),
            	    r.getQuantity() + "個"
            	);
        }
    }

    /**
     * トップセールス担当者を印字する。
     */
    public void printTopSalesRep() {

        Map<String, Integer> totals = aggregateByRep();

        String[] repNames =
            totals.keySet().toArray(new String[0]);

        System.out.println();
        System.out.println("========== トップセールス ==========");

        String topRep = null;
        int topTotal = 0;

        for (int i = 0; i < repNames.length; i++) {

            String rep = repNames[i];
            int amount = totals.get(rep);

            if (amount > topTotal) {
                topTotal = amount;
                topRep = rep;
            }
        }

        if (topRep != null) {
            System.out.printf(
                "1位: %-10s %,d円%n",
                topRep,
                topTotal
            );
        }
    }
}