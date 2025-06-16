package com.example.HotelReservations.DTO.Hotel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelDto {

    private UUID id;

    private String name;

    private String adTitle;

    private String city;

    private String address;

    private float distanceFromCenter;

    private int rating;

    private int ratingCount;
}
