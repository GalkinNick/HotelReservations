package com.example.HotelReservations.controller;

import com.example.HotelReservations.DTO.Room.RoomDtoCreated;
import com.example.HotelReservations.DTO.Room.RoomDtoRequest;
import com.example.HotelReservations.entity.RoomEntity;
import com.example.HotelReservations.mapper.RoomMapper;
import com.example.HotelReservations.service.HotelService;
import com.example.HotelReservations.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
@Slf4j
public class RoomController {

    private final RoomMapper roomMapper;

    private final RoomService roomServiceImpl;

    private final HotelService hotelServiceImpl;

    @GetMapping("/{id}")
    public ResponseEntity<RoomDtoRequest> finById(@PathVariable UUID id){
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
