package com.example.HotelReservations.service.impl;

import com.example.HotelReservations.entity.HotelEntity;
import com.example.HotelReservations.repository.HotelRepository;
import com.example.HotelReservations.service.HotelService;
import com.example.HotelReservations.utils.BeanUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Override
    public List<HotelEntity> findAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Page<HotelEntity> findAll(Specification spec, Pageable pageable) {
        return hotelRepository.findAll(spec, pageable);
    }

    @Override
    public HotelEntity findById(UUID id) {
        return hotelRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                MessageFormat.format("Новость с id {0} не найдена", id)
        ));
    }

    @Override
    public HotelEntity create(HotelEntity hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public HotelEntity update(UUID id, HotelEntity hotel) {

        HotelEntity existedHotel = findById(id);

        hotel.setId(id);

        BeanUtils.copyNonNullProperties(hotel, existedHotel);

        return hotelRepository.save(hotel);
    }

    @Override
    public void deleteById(UUID id) {
        hotelRepository.deleteById(id);
    }
}
