package com.example.HotelReservations.service;

import com.example.HotelReservations.entity.HotelEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

public interface HotelService {

    List<HotelEntity> findAll();

    Page<HotelEntity> findAll(Specification spec, Pageable pageable);

    HotelEntity findById(UUID id);

    HotelEntity create(HotelEntity hotel);

    HotelEntity update(UUID id, HotelEntity hotel);

    void deleteById(UUID id);
}
