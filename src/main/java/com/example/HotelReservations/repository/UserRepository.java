package com.example.HotelReservations.repository;

import com.example.HotelReservations.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    UserEntity findByName(String name);

    //boolean checkUserByUsernameAndEmail(String name, String email);

    boolean existsByName(String name);

    boolean existsByEmail(String email);
}
