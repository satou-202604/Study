package com.example.billing;

/**
 * DBを使わない学習用リポジトリ（完成形）
 */
public class InvoiceRepository {

    /**
     * 登録
     */
    public void insert(Invoice invoice) {
    	System.out.println("[BATCH] INSERT: " + invoice.getInvoiceId());
    }

    /**
     * 取得（未使用想定）
     */
    public Invoice findById(String invoiceId) {
        System.out.println("[MOCK SELECT] " + invoiceId);
        return null;
    }

    /**
     * 更新（ダミー）
     */
    public void updateStatus(String invoiceId, String status) {
        System.out.println("[MOCK UPDATE] " + invoiceId + " → " + status);
    }
}