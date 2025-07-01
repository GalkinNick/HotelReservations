package com.example.HotelReservations.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagedResponseDto<T> {

    private List<T> data;

    private long totalElement;

    public static <T> PagedResponseDto<T> of (List<T> data, long totalElement){
        return new PagedResponseDto<>(data, totalElement);
    }



}
