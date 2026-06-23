package com.example.billing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 請求書発行サービスクラス。
 */
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final ClientRepository clientRepository;

    /** インメモリキャッシュ（invoiceId → Invoice） */
    private final Map<String, Invoice> invoiceCache = new HashMap<>();

    /** インメモリキャッシュ（clientId → Client） */
    private final Map<String, Client> clientCache = new HashMap<>();

    public InvoiceService(
            InvoiceRepository invoiceRepository,
            ClientRepository clientRepository) {

        this.invoiceRepository = invoiceRepository;
        this.clientRepository = clientRepository;
    }

    /**
     * 取引先を登録する。
     */
    public void registerClient(Client client) {
        clientCache.put(client.getClientId(), client);
    }

    /**
     * 請求書を発行する。
     */
    public void issue(Invoice invoice) {
        invoiceCache.put(invoice.getInvoiceId(), invoice);
    }

    /**
     * 請求書サマリーを一覧印字する。
     */
    public void printInvoiceSummary(List<String> invoiceIds) {

        System.out.println("========== 請求書一覧 ==========");
        System.out.printf("%-18s %-22s %-12s %-12s %12s%n",
                "請求書番号",
                "取引先",
                "発行日",
                "支払期限",
                "税込合計");

        System.out.println(
                "-----------------------------------------------------------------------------------------------");

        for (String invoiceId : invoiceIds) {

            Invoice invoice = invoiceCache.get(invoiceId);

            if (invoice == null) {
                System.out.printf("%-18s [請求書が見つかりません]%n",
                        invoiceId);
                continue;
            }

            Client client = clientCache.get(invoice.getClientId());

            System.out.printf(
                    "%-18s %-22s %-12s %-12s %,12d円%n",
                    invoice.getInvoiceId(),
                    client != null ? client.getClientName() : "不明",
                    invoice.getIssueDate(),
                    invoice.getDueDate(),
                    invoice.calcTotalWithTax());
        }
    }

    /**
     * 請求書の詳細を印字する。
     */
    public void printInvoiceDetail(String invoiceId) {

        Invoice invoice = invoiceCache.get(invoiceId);

        if (invoice == null) {
            throw new InvoiceNotFoundException(invoiceId);
        }

        Client client = clientCache.get(invoice.getClientId());

        System.out.println("========================================");
        System.out.println("              請求書");
        System.out.println("========================================");

        System.out.printf("請求書番号 : %s%n", invoice.getInvoiceId());
        System.out.printf("発行日     : %s%n", invoice.getIssueDate());
        System.out.printf("支払期限   : %s%n", invoice.getDueDate());
        System.out.printf("ステータス : %s%n", invoice.getStatus());

        System.out.println("----------------------------------------");

        System.out.printf("請求先     : %s%n",
                client != null ? client.getClientName() : "不明");
        System.out.printf("住所       : %s%n",
                client != null ? client.getAddress() : "-");

        System.out.println("----------------------------------------");

        for (InvoiceDetail detail : invoice.getDetails()) {

            System.out.printf(
                    "  %-22s %4d個 %,10d円 %,12d円%n",
                    detail.getDescription(),
                    detail.getQuantity(),
                    detail.getUnitPrice(),
                    detail.calcSubtotal());
        }

        System.out.println("----------------------------------------");

        System.out.printf("合計（税込）: %,d円%n",
                invoice.calcTotalWithTax());

        System.out.println("========================================");
    }
}