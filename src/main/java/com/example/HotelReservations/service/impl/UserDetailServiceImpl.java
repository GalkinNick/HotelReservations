package com.example.HotelReservations.service.impl;

import com.example.HotelReservations.entity.UserEntity;
import com.example.HotelReservations.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Optional<UserEntity> userEntity = userRepository.findByName(username);
        return userRepository.findByName(username);
    }
}
