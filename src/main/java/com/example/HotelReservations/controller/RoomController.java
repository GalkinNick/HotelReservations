package com.example.HotelReservations.controller;

import com.example.HotelReservations.DTO.PagedResponseDto;
import com.example.HotelReservations.DTO.Room.RoomDtoCreated;
import com.example.HotelReservations.DTO.Room.RoomDtoRequest;
import com.example.HotelReservations.entity.BookingEntity;
import com.example.HotelReservations.entity.RoomEntity;
import com.example.HotelReservations.mapper.RoomMapper;
import com.example.HotelReservations.service.HotelService;
import com.example.HotelReservations.service.RoomService;
import com.example.HotelReservations.specifications.RoomSpecifications;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Subquery;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
@Slf4j
public class RoomController {

    private final RoomMapper roomMapper;

    private final RoomService roomServiceImpl;

    private final HotelService hotelServiceImpl;


    @GetMapping("/rooms")
    public ResponseEntity<PagedResponseDto<RoomDtoRequest>> getRooms(
            @RequestParam(required = false) UUID roomId,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) Integer guests,
            @RequestParam(required = false) LocalDate checkIn,
            @RequestParam(required = false) LocalDate checkOut,
            @RequestParam(required = false) Long hotelId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Specification<RoomEntity> spec = Specification.where(null);

        if (roomId != null) {
            spec = spec.and(RoomSpecifications.hasId(roomId));
        }
        if (description != null && !description.isEmpty()) {
            spec = spec.and(RoomSpecifications.hasDescription(description));
        }
        if (minPrice != null || maxPrice != null) {
            spec = spec.and(RoomSpecifications.hasPriceBetween(minPrice, maxPrice));
        }
        if (guests != null) {
            spec = spec.and(RoomSpecifications.hasGuests(guests));
        }
        if (hotelId != null) {
            spec = spec.and(RoomSpecifications.hasHotelId(hotelId));
        }
        spec = spec.and(RoomSpecifications.isAvailableBetween(checkIn, checkOut)); // Важно: присваиваем, т.к. может быть null

        Pageable pageable = PageRequest.of(page, size);

        Page<RoomEntity> roomPage = roomServiceImpl.findAll(spec, pageable);

        List<RoomDtoRequest> roomDTOs = roomPage.getContent().stream()
                .map(roomMapper::roomEntityToRoomRequestDto)
                .toList();

        PagedResponseDto<RoomDtoRequest> response = new PagedResponseDto<>(
                roomDTOs,
                roomPage.getTotalElements()

        );

        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<RoomDtoRequest> findById(@PathVariable UUID id){
        return ResponseEntity.ok(
                roomMapper.roomEntityToRoomRequestDto(roomServiceImpl.findById(id))
        );
    }

    @PostMapping
    public ResponseEntity<RoomDtoRequest> create(@RequestBody RoomDtoCreated roomDtoCreated){

        RoomEntity newRoomEntity = roomMapper.roomCreatedDtoToRoomEntity(roomDtoCreated);

        newRoomEntity.setHotel(hotelServiceImpl.findById(roomDtoCreated.getHotelId()));

        RoomEntity createdRoomEntity = roomServiceImpl.create(newRoomEntity);

        return ResponseEntity.status(HttpStatus.CREATED)
                        .body(roomMapper.roomEntityToRoomRequestDto(createdRoomEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomDtoRequest> update(@PathVariable UUID id,
                                                 @RequestBody RoomDtoCreated roomDtoCreated){

        RoomEntity existedRoom = roomServiceImpl.findById(id);

        roomMapper.updateEntityFromDto(roomDtoCreated, existedRoom);

        return ResponseEntity.ok(
                roomMapper.roomEntityToRoomRequestDto(roomServiceImpl.update(id, existedRoom))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
        roomServiceImpl.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }

}
