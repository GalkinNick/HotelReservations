package com.example.HotelReservations.service.impl;

import com.example.HotelReservations.entity.UserEntity;
import com.example.HotelReservations.repository.UserRepository;
import com.example.HotelReservations.service.UserService;
import com.example.HotelReservations.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity findById(UUID id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByName(username);
    }


    @Override
    public UserEntity create(UserEntity userEntity) {
        if (userRepository.existsByName(userEntity.getName())){
            throw new IllegalArgumentException("Username already exists");
        }

        if (userRepository.existsByEmail(userEntity.getEmail())){
            throw new IllegalArgumentException("Email already exists");
        }

        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity update(UUID id, UserEntity userEntity) {

        if (!userRepository.existsById(id)){
            throw new IllegalArgumentException("User not found");
        }

        UserEntity existedUser = findById(id);

        userEntity.setId(id);

        BeanUtils.copyNonNullProperties(userEntity, existedUser);

        return userRepository.save(userEntity);
    }

    @Override
    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

}
