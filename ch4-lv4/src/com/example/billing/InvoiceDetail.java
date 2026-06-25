package com.example.billing;

public class InvoiceDetail {

    private String detailId;
    private String invoiceId;
    private String description;
    private int quantity;
    private int unitPrice;

    public InvoiceDetail(String detailId, String invoiceId,
                         String description, int quantity, int unitPrice) {
        this.detailId = detailId;
        this.invoiceId = invoiceId;
        this.description = description;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public int calcSubtotal() {
        return quantity * unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getUnitPrice() {
        return unitPrice;
    }
}