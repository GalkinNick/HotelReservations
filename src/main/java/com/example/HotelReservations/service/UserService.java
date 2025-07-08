package com.example.HotelReservations.service;

import com.example.HotelReservations.entity.UserEntity;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<UserEntity> findAll();

    UserEntity findById(UUID id);

    UserEntity findByUsername(String username);

    UserEntity create(UserEntity userEntity);

    UserEntity update(UUID id, UserEntity userEntity);

    void deleteById(UUID id);

    void registerUser(String userId);
}
