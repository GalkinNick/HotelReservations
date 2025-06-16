package com.example.HotelReservations.mapper;

import com.example.HotelReservations.DTO.Hotel.HotelCreatedDto;
import com.example.HotelReservations.DTO.Hotel.HotelDto;
import com.example.HotelReservations.DTO.Hotel.HotelListResponseDto;
import com.example.HotelReservations.entity.HotelEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface HotelMapper {

    HotelEntity hotelCreatedDtoToHotelEntity(HotelCreatedDto hotelCreatedDto);

    void updateHotelEntityFromDto(HotelCreatedDto hotelCreatedDto, @MappingTarget HotelEntity hotelEntity);

    HotelDto hotelEntityToHotelDto(HotelEntity hotelEntity);


    default HotelListResponseDto hotelsListToHotelListResponseDto(List<HotelEntity> hotelsList){
        HotelListResponseDto hotelsListResponse = new HotelListResponseDto();

        hotelsListResponse.setHotels(
                hotelsList.stream()
                        .map(this::hotelEntityToHotelDto)
                        .collect(Collectors.toList()));

        return hotelsListResponse;
    }
}
