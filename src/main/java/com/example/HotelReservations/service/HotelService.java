package com.example.HotelReservations.service;

import com.example.HotelReservations.entity.HotelEntity;

import java.util.List;
import java.util.UUID;

public interface HotelService {

    List<HotelEntity> findAll();

    HotelEntity findById(UUID id);

    HotelEntity create(HotelEntity hotel);

    HotelEntity update(UUID id, HotelEntity hotel);

    void deleteById(UUID id);
}
