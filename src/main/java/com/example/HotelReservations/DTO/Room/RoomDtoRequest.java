package com.example.HotelReservations.DTO.Room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDtoRequest {

    private UUID id;

    private String name;

    private String description;

    private int number;

    private BigDecimal price;

    private int maxPeople;

    private List<LocalDate> unavailableDates;

    private UUID hotelId;

}
