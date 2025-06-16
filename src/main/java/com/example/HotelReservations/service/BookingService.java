package com.example.HotelReservations.service;

import com.example.HotelReservations.entity.BookingEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface BookingService {

    List<BookingEntity> findAll();

    BookingEntity bookRoom(UUID roomId, UUID userId, LocalDate startDate, LocalDate endDate);

    List<BookingEntity> findByUserId(UUID userId);
}
