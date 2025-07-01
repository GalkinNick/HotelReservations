package com.example.HotelReservations.controller;

import com.example.HotelReservations.DTO.Hotel.HotelCreatedDto;
import com.example.HotelReservations.DTO.Hotel.HotelDto;
import com.example.HotelReservations.DTO.Hotel.HotelListResponseDto;
import com.example.HotelReservations.DTO.PagedResponseDto;
import com.example.HotelReservations.entity.HotelEntity;
import com.example.HotelReservations.mapper.HotelMapper;
import com.example.HotelReservations.service.HotelService;
import com.example.HotelReservations.specifications.HotelSpecifications;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
@Slf4j
public class HotelController {

    private final HotelMapper hotelMapper;

    private final HotelService hotelServiceImpl;

    @GetMapping
    public ResponseEntity<HotelListResponseDto> findAll(){
        HotelListResponseDto hotelsListResponse = hotelMapper.hotelsListToHotelListResponseDto(hotelServiceImpl.findAll());
        return ResponseEntity.ok(hotelsListResponse);
    }

    @GetMapping("/get")
    public ResponseEntity<PagedResponseDto<HotelDto>> getHotels(
            @RequestParam(required = false) UUID id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String adTitle,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) float distanceFromCenter,
            @RequestParam(required = false) int rating,
            @RequestParam(required = false) int ratingCount,
            Pageable pageable){

        Specification<HotelEntity> spec = Specification.where(null); // Начинаем с пустого условия

        if (id != null) {
            spec = spec.and(HotelSpecifications.hasId(id));
        }
        if (name != null && !name.isEmpty()) {
            spec = spec.and(HotelSpecifications.hasName(name));
        }
        if (adTitle != null && !adTitle.isEmpty()) {
            spec = spec.and(HotelSpecifications.hasAdTitle(adTitle));
        }
        if (city != null && !city.isEmpty()) {
            spec = spec.and(HotelSpecifications.hasCity(city));
        }
        if (address != null && !address.isEmpty()) {
            spec = spec.and(HotelSpecifications.hasAddress(address));
        }
        if (distanceFromCenter != 0) {
            spec = spec.and(HotelSpecifications.hasDistanceToCenter(distanceFromCenter));
        }
        if (rating != 0) {
            spec = spec.and(HotelSpecifications.hasRating(rating));
        }
        if (ratingCount != 0) {
            spec = spec.and(HotelSpecifications.hasRatingCount(ratingCount));
        }

        Page<HotelEntity> hotelPage = hotelServiceImpl.findAll(spec, pageable);

        List<HotelDto> hotelDTOs = hotelPage.getContent().stream()
                .map(hotelMapper::hotelEntityToHotelDto)
                .toList();

        PagedResponseDto<HotelDto> response = new PagedResponseDto<>(
                hotelDTOs,
                hotelPage.getTotalElements()
        );

        return ResponseEntity.ok(response);

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


    @PatchMapping("/{hotelId}/rating")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<?> updateHotelRating(@PathVariable UUID hotelId,
                                               @RequestParam("newMark") int newMark){
        if (newMark < 1 || newMark > 5){
            return ResponseEntity.badRequest().body("Новая оценка должна быть от 1 до 5");
        }

        try {
            HotelEntity hotelEntity = hotelServiceImpl.findById(hotelId);

            if (hotelEntity == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Отель не найден");


            float currentRating = hotelEntity.getRating();

            int numberOfRating = hotelEntity.getRatingCount();

            float totalRating = currentRating * numberOfRating;

            totalRating = (totalRating - currentRating) + newMark;

            float newRating = totalRating / numberOfRating;

            BigDecimal bd = new BigDecimal(newRating).setScale(1, RoundingMode.HALF_UP);
            newRating = bd.floatValue();

            numberOfRating++;


            hotelEntity.setRating(newRating);
            hotelEntity.setRatingCount(numberOfRating);

            hotelServiceImpl.update(hotelId, hotelEntity);

            return ResponseEntity.ok("Рейтинг отеля успешно обновлен. Новый рейтинг: " + newRating);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при обновлении рейтинга: " + e.getMessage());
        }


    }

}
