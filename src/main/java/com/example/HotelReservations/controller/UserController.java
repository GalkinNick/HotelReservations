package com.example.HotelReservations.controller;

import com.example.HotelReservations.DTO.User.UserCreatedDto;
import com.example.HotelReservations.DTO.User.UserResponseDto;
import com.example.HotelReservations.entity.UserEntity;
import com.example.HotelReservations.mapper.UserMapper;
import com.example.HotelReservations.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userServiceImpl;
    private final UserMapper userMapper;

    @GetMapping
    public List<UserResponseDto> findAll(){
        return userServiceImpl.findAll().stream()
                .map(userMapper::userEntityToUserResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable UUID id){
        return ResponseEntity.ok(
                userMapper.userEntityToUserResponseDto(userServiceImpl.findById(id))
        );
    }


    @GetMapping("/{name}")
    public ResponseEntity<UserResponseDto> findByName(@PathVariable String name){
        return ResponseEntity.ok(
                userMapper.userEntityToUserResponseDto(userServiceImpl.findByUsername(name))
        );
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> create(@RequestBody UserCreatedDto createdDto){
        UserEntity newUserEntity = userMapper.userCreatedDtoToUSerEntity(createdDto);

        UserEntity createdUser = userServiceImpl.create(newUserEntity);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.userEntityToUserResponseDto(createdUser));

    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> update(@PathVariable UUID id,
                                                  @RequestBody UserCreatedDto createdDto){

        UserEntity existedUser = userServiceImpl.findById(id);

        userMapper.updateEntityFromDto(createdDto, existedUser);

        return ResponseEntity.ok(
                userMapper.userEntityToUserResponseDto(userServiceImpl.update(id, existedUser))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        userServiceImpl.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }


}
