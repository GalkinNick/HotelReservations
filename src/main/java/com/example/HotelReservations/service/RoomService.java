package com.example.HotelReservations.service;

import com.example.HotelReservations.entity.RoomEntity;

import java.util.List;
import java.util.UUID;

public interface RoomService {

    List<RoomEntity> findAll();

    RoomEntity findById(UUID id);

    RoomEntity create(RoomEntity room);

    RoomEntity update(UUID id, RoomEntity room);

    void deleteById(UUID id);
}
