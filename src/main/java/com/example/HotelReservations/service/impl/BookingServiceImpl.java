package com.example.HotelReservations.service.impl;

import com.example.HotelReservations.entity.BookingEntity;
import com.example.HotelReservations.entity.RoomEntity;
import com.example.HotelReservations.entity.UserEntity;
import com.example.HotelReservations.repository.BookingRepository;
import com.example.HotelReservations.repository.RoomRepository;
import com.example.HotelReservations.repository.UserRepository;
import com.example.HotelReservations.service.BookingService;
import com.example.HotelReservations.service.RoomService;
import com.example.HotelReservations.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    private final UserRepository userRepository;

    private final RoomRepository roomRepository;


    @Override
    public List<BookingEntity> findAll() {
        return bookingRepository.findAll();
    }

    @Override
    public Page<BookingEntity> findAll(Pageable pageable) {
        return bookingRepository.findAll(pageable);
    }


    @Override
    @Transactional
    public BookingEntity bookRoom(UUID roomId, UUID userId, LocalDate startDate, LocalDate endDate) {
        RoomEntity roomEntity = roomRepository.findById(roomId).orElseThrow(() -> new EntityNotFoundException("Room not found with id: " + roomId));
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date must be before end date.");
        }

        /*if (bookingRepository.countOverlappingBookings(roomId, startDate, endDate) > 0) {
            throw new IllegalArgumentException("Room is not available for the selected dates.");
        }*/


        BookingEntity booking = BookingEntity.builder()
                .startDate(startDate)
                .endDate(endDate)
                .room(roomEntity)
                .user(userEntity)
                .build();


        return bookingRepository.save(booking);
    }

    @Override
    public List<BookingEntity> findByUserId(UUID userId) {
        return bookingRepository.findByUserId(userId);
    }
}
