package com.example.HotelReservations.service;

import com.example.HotelReservations.entity.RoomEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

public interface RoomService {

    List<RoomEntity> findAll();

    Page<RoomEntity> findAll(Specification specification, Pageable pageable);

    RoomEntity findById(UUID id);

    RoomEntity create(RoomEntity room);

    RoomEntity update(UUID id, RoomEntity room);

    void deleteById(UUID id);
}
