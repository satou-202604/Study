package com.example.billing;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class InvoiceRepository {

    private final DataSource dataSource;

    public InvoiceRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 登録
     */
    public void insert(Invoice invoice) {

        String sql = """
            INSERT INTO invoices
            (invoice_id, client_id, issue_date, due_date, status)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, invoice.getInvoiceId());
            ps.setString(2, invoice.getClientId());
            ps.setDate(3, java.sql.Date.valueOf(invoice.getIssueDate()));
            ps.setDate(4, Date.valueOf(invoice.getDueDate()));
            ps.setString(5, invoice.getStatus());

            int result = ps.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("INSERT失敗件数異常: " + result);
            }

        } catch (SQLException e) {
            throw new RuntimeException("INSERT失敗: " + invoice.getInvoiceId(), e);
        }
    }

    /**
     * 取得（ヘッダのみ）
     */
    public Invoice findById(String invoiceId) {

        String sql = """
            SELECT invoice_id, client_id, issue_date, due_date, status
            FROM invoices
            WHERE invoice_id = ?
        """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, invoiceId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    Invoice inv = new Invoice(
                            rs.getString("invoice_id"),
                            rs.getString("client_id"),
                            rs.getDate("issue_date").toLocalDate(),
                            rs.getDate("due_date").toLocalDate()
                    );

                    inv.setStatus(rs.getString("status"));
                    return inv;
                }
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("SELECT失敗: " + invoiceId, e);
        }
    }

    /**
     * ステータス更新
     */
    public void updateStatus(String invoiceId, String status) {

        String sql = """
            UPDATE invoices
            SET status = ?, updated_at = NOW()
            WHERE invoice_id = ?
        """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setString(2, invoiceId);

            int result = ps.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("UPDATE件数異常: " + result);
            }

        } catch (SQLException e) {
            throw new RuntimeException("UPDATE失敗: " + invoiceId, e);
        }
    }
}