package com.example.HotelReservations.mapper;

import com.example.HotelReservations.DTO.Room.RoomDtoCreated;
import com.example.HotelReservations.DTO.Room.RoomDtoRequest;
import com.example.HotelReservations.entity.RoomEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    RoomEntity roomCreatedDtoToRoomEntity(RoomDtoCreated roomDto);

    RoomDtoCreated roomEntityToRoomCreatedDto(RoomEntity roomEntity);

    RoomDtoRequest roomEntityToRoomRequestDto(RoomEntity roomEntity);

    void updateEntityFromDto(RoomDtoCreated dto, @MappingTarget RoomEntity entity);
}
