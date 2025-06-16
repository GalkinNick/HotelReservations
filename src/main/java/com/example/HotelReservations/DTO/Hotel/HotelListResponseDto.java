package com.example.HotelReservations.DTO.Hotel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelListResponseDto {

    private List<HotelDto> hotels = new ArrayList<>();

    private long totalElements;

    private int totalPages;

    private int currentPage;
}
