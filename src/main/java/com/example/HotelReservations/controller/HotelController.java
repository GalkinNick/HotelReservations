package com.example.HotelReservations.controller;

import com.example.HotelReservations.DTO.Hotel.HotelCreatedDto;
import com.example.HotelReservations.DTO.Hotel.HotelDto;
import com.example.HotelReservations.DTO.Hotel.HotelListResponseDto;
import com.example.HotelReservations.entity.HotelEntity;
import com.example.HotelReservations.mapper.HotelMapper;
import com.example.HotelReservations.service.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
@Slf4j
public class HotelController {

    private final HotelMapper hotelMapper;

    private final HotelService hotelServiceImpl;

    @GetMapping
    public ResponseEntity<HotelListResponseDto> findAll(){

        log.info("Do this controller");
        HotelListResponseDto hotelsListResponse = hotelMapper.hotelsListToHotelListResponseDto(hotelServiceImpl.findAll());
        log.info("Do this controller second");
        return ResponseEntity.ok(hotelsListResponse);
    }


    @GetMapping("/{id}")
    public ResponseEntity<HotelDto> findById(@PathVariable UUID id){
        return ResponseEntity.ok(
                hotelMapper.hotelEntityToHotelDto(hotelServiceImpl.findById(id))
        );
    }

    @PostMapping
    public ResponseEntity<HotelDto> create(@RequestBody HotelCreatedDto hotelCreatedDto){

        HotelEntity newHotelEntity = hotelServiceImpl.create(hotelMapper.hotelCreatedDtoToHotelEntity(hotelCreatedDto));


        return ResponseEntity.status(HttpStatus.CREATED)
                .body(hotelMapper.hotelEntityToHotelDto(newHotelEntity));

    }



    @PutMapping("/{id}")
    public ResponseEntity<HotelDto> update(@PathVariable UUID id,
                                           @RequestBody HotelCreatedDto hotelCreatedDto){
        HotelEntity existingHotel = hotelServiceImpl.findById(id);

        hotelMapper.updateHotelEntityFromDto(hotelCreatedDto, existingHotel);

        return ResponseEntity.ok(
                hotelMapper.hotelEntityToHotelDto(hotelServiceImpl.update(id, existingHotel))
        );

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
        hotelServiceImpl.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }

}
