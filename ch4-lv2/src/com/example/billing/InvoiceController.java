package com.example.billing;

/**
 * 請求書発行のコントローラクラス。
 */
public class InvoiceController {

    private final InvoiceService service;

    public InvoiceController(InvoiceService service) {
        this.service = service;
    }

    /**
     * 請求書発行リクエストを処理する。
     */
    public void handleIssue(String invoiceId,
                            Client client,
                            Invoice invoice) {

        service.issue(invoice);
        service.printInvoice(invoice, client);
    }

    /**
     * 請求書承認リクエストを処理する。
     */
    public void handleApprove(String invoiceId) {

        Invoice approvedInvoice = service.approve(invoiceId);

        // 必要なら approvedInvoice を利用する
    }
}