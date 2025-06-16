package com.example.HotelReservations.DTO.Booking;

import com.example.HotelReservations.entity.RoomEntity;
import com.example.HotelReservations.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingCreatedDto {

    private LocalDate startDate;

    private LocalDate endDate;

    private UUID room;

    private UUID user;
}
