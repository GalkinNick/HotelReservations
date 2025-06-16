package com.example.HotelReservations.DTO.Hotel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelCreatedDto {

    //private UUID id;

    private String name;

    private String adTitle;

    private String city;

    private String address;

    private float distanceFromCenter;

}
