package com.example.billing;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 請求書発行サービスクラス
 */
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    /** インメモリキャッシュ（invoiceId → Invoice） */
    private final Map<String, Invoice> cache = new LinkedHashMap<>();

    /** インメモリキャッシュ（clientId → Client） */
    private final Map<String, Client> clientCache = new LinkedHashMap<>();

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    /**
     * 取引先を登録
     */
    public void registerClient(Client client) {
        clientCache.put(client.getClientId(), client);
    }

    /**
     * 請求書発行
     */
    public void issue(Invoice invoice) {
        cache.put(invoice.getInvoiceId(), invoice);
    }

    /**
     * 一括登録
     */
    public void bulkInsert() {
        for (Invoice inv : cache.values()) {
            invoiceRepository.insert(inv);
        }
    }

    /**
     * 月次集計レポート
     */
    public void printMonthlyReport() {

        System.out.println(
                "========== 月次集計レポート ==========");

        System.out.printf(
                "%-16s  %-20s  %12s%n",
                "請求書番号",
                "取引先",
                "税込合計");

        System.out.println(
                "------------------------------------------------------------");

        int grandTotal = 0;

        for (Invoice inv : cache.values()) {

            Client client =
                    clientCache.get(inv.getClientId());

            String clientName =
                    client.getClientName();

            System.out.printf(
                    "%-16s  %-20s  %,12d円%n",
                    inv.getInvoiceId(),
                    clientName,
                    inv.calcTotalWithTax());

            grandTotal +=
                    inv.calcTotalWithTax();
        }

        System.out.println(
                "------------------------------------------------------------");

        System.out.printf(
                "合計：%,d円%n",
                grandTotal);
    }

    /**
     * 支払期限順ソート
     */
    public void printSortedByDueDate() {

        List<Invoice> invoices =
                new ArrayList<>(cache.values());

        Invoice[] arr =
                invoices.toArray(new Invoice[0]);

        // バブルソート
        for (int i = 0; i < arr.length - 1; i++) {

            for (int j = 0;
                 j < arr.length - i - 1;
                 j++) {

                if (arr[j].getDueDate()
                        .isAfter(arr[j + 1].getDueDate())) {

                    Invoice tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }

        System.out.println(
                "\n========== 支払期限別ソート ==========");

        for (Invoice inv : arr) {

            System.out.printf(
                    "%-16s  %-12s  %,12d円%n",
                    inv.getInvoiceId(),
                    inv.getDueDate(),
                    inv.calcTotalWithTax());
        }
    }
}