package com.example.billing;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class InvoiceBatchService {

    private final InvoiceRepository repository;

    private final Map<String, Invoice> cache = new LinkedHashMap<>();
    private final Map<String, Client> clientCache = new LinkedHashMap<>();

    public InvoiceBatchService(InvoiceRepository repository) {
        this.repository = repository;
    }

    public void registerClient(Client client) {
        clientCache.put(client.getClientId(), client);
    }

    public void addInvoice(Invoice invoice) {
        cache.put(invoice.getInvoiceId(), invoice);
    }

    /**
     * バッチ登録
     */
    public void batchInsert() {

        System.out.println("[BATCH] 請求書バッチ登録開始: " + cache.size() + " 件");

        for (Invoice inv : cache.values()) {
            repository.insert(inv);
        }

        System.out.println("[BATCH] バッチ登録完了");
    }

    /**
     * 未払い請求書一覧
     */
    public void printUnpaidInvoices(LocalDate baseDate) {

        System.out.println("========== 未払い請求書一覧 ==========");

        for (Invoice inv : cache.values()) {

            if ("DRAFT".equals(inv.getStatus())
                    && !inv.getDueDate().isBefore(baseDate)) {

                Client client = clientCache.get(inv.getClientId());

                String name = (client != null)
                        ? client.getClientName()
                        : "不明";

                System.out.printf("%-16s %-24s %-12s %,12d円%n",
                        inv.getInvoiceId(),
                        name,
                        inv.getDueDate(),
                        inv.calcTotalWithTax());
            }
        }
    }

    /**
     * 期限超過請求書
     */
    public void printOverdueInvoices(LocalDate baseDate) {

        System.out.println("\n========== 期限超過請求書 ==========");

        for (Invoice inv : cache.values()) {

            if ("DRAFT".equals(inv.getStatus())
                    && inv.getDueDate().isBefore(baseDate)) {

                Client client = clientCache.get(inv.getClientId());

                String name = (client != null)
                        ? client.getClientName()
                        : "不明";

                System.out.printf("%-16s %-24s %-12s %,12d円 【要督促】%n",
                        inv.getInvoiceId(),
                        name,
                        inv.getDueDate(),
                        inv.calcTotalWithTax());
            }
        }
    }

    /**
     * 総合計
     */
    public int calcGrandTotal() {

        int total = 0;

        for (Invoice inv : cache.values()) {
            total += inv.calcTotalWithTax();
        }

        return total;
    }

    /**
     * 取引先別集計
     */
    public Map<String, Integer> aggregateByClient() {

        Map<String, Integer> result = new LinkedHashMap<>();

        for (Invoice inv : cache.values()) {

            result.put(
                    inv.getClientId(),
                    result.getOrDefault(inv.getClientId(), 0)
                            + inv.calcTotalWithTax()
            );
        }

        return result;
    }
}