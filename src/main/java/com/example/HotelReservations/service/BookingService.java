package com.example.HotelReservations.service;

import com.example.HotelReservations.entity.BookingEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface BookingService {

    List<BookingEntity> findAll();

    Page<BookingEntity> findAll(Pageable pageable);

    BookingEntity bookRoom(UUID roomId, UUID userId, LocalDate startDate, LocalDate endDate);

    List<BookingEntity> findByUserId(UUID userId);
}
