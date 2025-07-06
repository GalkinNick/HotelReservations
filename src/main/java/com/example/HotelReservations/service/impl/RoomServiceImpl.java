package com.example.HotelReservations.service.impl;

import com.example.HotelReservations.entity.HotelEntity;
import com.example.HotelReservations.entity.RoomEntity;
import com.example.HotelReservations.repository.RoomRepository;
import com.example.HotelReservations.service.RoomService;
import com.example.HotelReservations.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public List<RoomEntity> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Page<RoomEntity> findAll(Specification specification, Pageable pageable) {
        return roomRepository.findAll(specification, pageable);
    }

    @Override
    public RoomEntity findById(UUID id) {
        return roomRepository.findById(id).orElseThrow();
    }

    @Override
    public RoomEntity create(RoomEntity room) {
        return roomRepository.save(room);
    }

    @Override
    public RoomEntity update(UUID id, RoomEntity room) {
        RoomEntity existedRoom = findById(id);

        room.setId(id);

        BeanUtils.copyNonNullProperties(room, existedRoom);

        return roomRepository.save(room);
    }

    @Override
    public void deleteById(UUID id) {
        roomRepository.deleteById(id);
    }
}
