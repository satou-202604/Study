package com.example.demo.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Reservation;

public interface ReservationRepository
        extends JpaRepository<Reservation, Integer> {

    // 重複予約チェック
    boolean existsByReservationDateAndReservationTime(
            LocalDate reservationDate,
            LocalTime reservationTime);

    // 日付検索
    List<Reservation> findByReservationDate(
            LocalDate reservationDate);

    // 会員番号 または お客様名検索
    List<Reservation>
    findByMemberNoContainingOrCustomerNameContaining(
            String memberNo,
            String customerName);
}