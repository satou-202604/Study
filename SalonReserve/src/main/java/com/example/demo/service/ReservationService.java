package com.example.demo.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Reservation;
import com.example.demo.repository.ReservationRepository;
@Service
public class ReservationService {

    @Autowired
    private ReservationRepository repository;

    // 全件取得
    public List<Reservation> findAll() {
        return repository.findAll();
    }

    // ID検索
    public Reservation findById(Integer id) {
        Optional<Reservation> result = repository.findById(id);
        return result.orElse(null);
    }

    // 登録・更新
    public void save(Reservation reservation) {

        boolean duplicate =
                repository.existsByReservationDateAndReservationTime(
                        reservation.getReservationDate(),
                        reservation.getReservationTime());

        if (duplicate) {

            // 編集時は自分自身ならOK
            List<Reservation> reservations = repository.findAll();

            for (Reservation r : reservations) {

                if (r.getReservationDate().equals(reservation.getReservationDate())
                        && r.getReservationTime().equals(reservation.getReservationTime())) {

                    if (!r.getId().equals(reservation.getId())) {
                        throw new IllegalArgumentException(
                                "この日時はすでに予約されています");
                    }
                }
            }
        }

        repository.save(reservation);
    }

    // 削除
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    // 日付検索
    public List<Reservation> findByDate(LocalDate date) {
        return repository.findByReservationDate(date);
    }

    // 時間検索
    public List<Reservation> findByTime(LocalTime time) {
        return repository.findAll()
                .stream()
                .filter(r -> r.getReservationTime().equals(time))
                .toList();
    }
 // ======================
 // 会員番号・お客様名検索
 // ======================
 public List<Reservation> search(String keyword) {

     return repository
             .findByMemberNoContainingOrCustomerNameContaining(
                     keyword,
                     keyword);
 }
}