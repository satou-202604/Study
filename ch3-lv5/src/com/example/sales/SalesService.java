package com.example.sales;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 売上集計サービスクラス。
 */
public class SalesService {

    private final SalesRepository repository;

    /** 担当者ごとの月次目標金額（円） */
    private final Map<String, Integer> targets = new LinkedHashMap<>();

    public SalesService(SalesRepository repository) {
        this.repository = repository;
    }

    /**
     * 月次目標を設定する。
     */
    public void setTarget(String repName, int target) {
        targets.put(repName, target);
    }

    /**
     * 売上レコードを登録する。
     */
    public void register(SalesRecord record) {

        if (record.getQuantity() <= 0) {
            throw new InvalidSalesDataException(
                "quantity",
                record.getQuantity()
            );
        }

        repository.add(record);
    }

    /**
     * 担当者別売上合計。
     */
    public Map<String, Integer> aggregateByRep() {

        Map<String, Integer> result =
            new LinkedHashMap<>();

        for (SalesRecord r : repository.findAll()) {

            result.put(
                r.getSalesRepName(),
                result.getOrDefault(
                    r.getSalesRepName(),
                    0
                ) + r.calcAmount()
            );
        }

        return result;
    }

    /**
     * 商品別売上集計。
     */
    public Map<String, Integer> aggregateByProduct() {

        Map<String, Integer> result =
            new LinkedHashMap<>();

        for (SalesRecord r : repository.findAll()) {

            result.put(
                r.getProductId(),
                result.getOrDefault(
                    r.getProductId(),
                    0
                ) + r.calcAmount()
            );
        }

        return result;
    }

    /**
     * 商品別数量集計。
     */
    public Map<String, Integer> aggregateQuantityByProduct() {

        Map<String, Integer> result =
            new LinkedHashMap<>();

        for (SalesRecord r : repository.findAll()) {

            result.put(
                r.getProductId(),
                result.getOrDefault(
                    r.getProductId(),
                    0
                ) + r.getQuantity()
            );
        }

        return result;
    }

    /**
     * 商品ID → カテゴリ。
     */
    private Map<String, String> buildProductCategoryMap() {

        Map<String, String> map =
            new LinkedHashMap<>();

        for (SalesRecord r : repository.findAll()) {

            map.put(
                r.getProductId(),
                r.getCategory()
            );
        }

        return map;
    }

    /**
     * 商品別売上ランキング。
     */
    public void printProductRanking() {

        Map<String, Integer> amounts =
            aggregateByProduct();

        Map<String, Integer> quantities =
            aggregateQuantityByProduct();

        Map<String, String> categoryMap =
            buildProductCategoryMap();

        List<Map.Entry<String, Integer>> sortedAmounts =
            new ArrayList<>(amounts.entrySet());

        sortedAmounts.sort(
            (a, b) ->
                Integer.compare(
                    b.getValue(),
                    a.getValue()
                )
        );

        System.out.println(
            "========== 商品別売上ランキング =========="
        );
        System.out.printf(
        	    "%-4s %-10s %-12s %12s %6s%n",
        	    "順位",
        	    "商品ID",
        	    "カテゴリ",
        	    "売上金額",
        	    "数量"
        	);

        int rank = 1;

        for (Map.Entry<String, Integer> entry
                : sortedAmounts) {

            String productId =
                entry.getKey();

            String rankLabel =
                rank + "位";

            System.out.printf(
            	    "%-4s %-10s %-12s %12s %6s%n",
            	    rankLabel,
            	    productId,
            	    categoryMap.getOrDefault(productId, "不明"),
            	    String.format("%,d円", entry.getValue()),
            	    quantities.getOrDefault(productId, 0) + "個"
            	);
            rank++;
        }
    }

    /**
     * 担当者別達成率レポート。
     */
    public void printRepAchievementReport() {

        Map<String, Integer> actuals =
            aggregateByRep();

        System.out.println(
            "\n========== 担当者別達成率 =========="
        );

        System.out.printf(
            "%-10s %12s %12s %8s%n",
            "担当者",
            "実績（円）",
            "目標（円）",
            "達成率"
        );

        for (Map.Entry<String, Integer> e
                : actuals.entrySet()) {

            String repName =
                e.getKey();

            int actual =
                e.getValue();

            Integer target =
                targets.get(repName);

            if (target == null) {

                System.out.printf(
                    "%-10s %,12d %15s%n",
                    repName,
                    actual,
                    "目標未設定"
                );

            } else {

                double rate =
                    (double) actual
                    / target * 100.0;

                System.out.printf(
                    "%-10s %,12d %,12d %7.1f%%%n",
                    repName,
                    actual,
                    target,
                    rate
                );
            }
        }
    }
}