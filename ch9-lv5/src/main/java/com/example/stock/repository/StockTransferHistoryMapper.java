package com.example.stock.repository;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.example.stock.entity.StockTransferHistoryEntity;

@Mapper
public interface StockTransferHistoryMapper {

    @Select("SELECT id, transfer_no, from_warehouse_code, to_warehouse_code, product_code, " +
            "quantity, status, executed_by, executed_at FROM stock_transfer_histories " +
            "WHERE from_warehouse_code = #{warehouseCode} OR to_warehouse_code = #{warehouseCode} " +
            "ORDER BY executed_at DESC")
    List<StockTransferHistoryEntity> findByWarehouse(@Param("warehouseCode") String warehouseCode);

    @Insert("INSERT INTO stock_transfer_histories (transfer_no, from_warehouse_code, to_warehouse_code, " +
            "product_code, quantity, status, executed_by, executed_at) " +
            "VALUES (#{transferNo}, #{fromWarehouseCode}, #{toWarehouseCode}, #{productCode}, " +
            "#{quantity}, #{status}, #{executedBy}, #{executedAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(StockTransferHistoryEntity history);
}