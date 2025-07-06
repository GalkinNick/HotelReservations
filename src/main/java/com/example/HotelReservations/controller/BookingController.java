package com.example.HotelReservations.controller;

import com.example.HotelReservations.DTO.Booking.BookingCreatedDto;
import com.example.HotelReservations.DTO.Booking.BookingResponseDto;
import com.example.HotelReservations.DTO.PagedResponseDto;
import com.example.HotelReservations.entity.BookingEntity;
import com.example.HotelReservations.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/booking")
@RequiredArgsConstructor
@Slf4j
public class BookingController {

    private final BookingService bookingServiceImpl;


    @PostMapping
    public ResponseEntity<BookingResponseDto> bookRoom(@RequestBody BookingCreatedDto createdDto){
        try {
            BookingEntity bookingEntity = bookingServiceImpl.bookRoom(
                    createdDto.getRoom(),
                    createdDto.getUser(),
                    createdDto.getStartDate(),
                    createdDto.getEndDate()
            );

            BookingResponseDto responseDto = new BookingResponseDto(
                    bookingEntity.getId(),
                    bookingEntity.getStartDate(),
                    bookingEntity.getEndDate(),
                    bookingEntity.getUser().getId(),
                    bookingEntity.getRoom().getId()
            );

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(responseDto);

        } catch (Exception ex){
            log.info(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }


    @GetMapping("/bookings")
    public ResponseEntity<PagedResponseDto<BookingEntity>> getBookings(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<BookingEntity> bookingPage = bookingServiceImpl.findAll(pageable);

        PagedResponseDto<BookingEntity> response = new PagedResponseDto<>(
                bookingPage.getContent(),
                bookingPage.getTotalElements()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<BookingResponseDto>> findAll(){
        List<BookingEntity> bookings = bookingServiceImpl.findAll();

        List<BookingResponseDto> bookingResponseDtoList = bookings.stream()
                .map(booking -> new BookingResponseDto(
                        booking.getId(),
                        booking.getStartDate(),
                        booking.getEndDate(),
                        booking.getUser().getId(),
                        booking.getRoom().getId()
                ))
                .toList();

        return ResponseEntity.ok(bookingResponseDtoList);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingResponseDto>> getBookingsByUserId(@PathVariable UUID userId){
        List<BookingEntity> bookings = bookingServiceImpl.findByUserId(userId);

        List<BookingResponseDto> bookingResponseDtoList = bookings.stream()
                .map(booking -> new BookingResponseDto(
                        booking.getId(),
                        booking.getStartDate(),
                        booking.getEndDate(),
                        booking.getUser().getId(),
                        booking.getRoom().getId()
                ))
                .toList();

        return ResponseEntity.ok(bookingResponseDtoList);
    }


}
