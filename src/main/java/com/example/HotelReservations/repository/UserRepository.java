package com.example.HotelReservations.repository;

import com.example.HotelReservations.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    //Optional<UserEntity> findByName(String name);

    UserEntity findByName(String name);

    //boolean checkUserByUsernameAndEmail(String name, String email);

    boolean existsByName(String name);

    boolean existsByEmail(String email);
}
