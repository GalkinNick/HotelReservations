package com.example.HotelReservations.DTO.Booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDto {

    private UUID id;

    private LocalDate startDate;

    private LocalDate endDate;

    private UUID user;

    private UUID room;

}
