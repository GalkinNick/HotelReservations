package com.example.HotelReservations.mapper;

import com.example.HotelReservations.DTO.User.UserCreatedDto;
import com.example.HotelReservations.DTO.User.UserResponseDto;
import com.example.HotelReservations.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    //@Mapping(target = "role", expression = "java(UserEntity.RoleType.valueOf(userCreatedDto.getRole().toUpperCase()))")
    UserEntity userCreatedDtoToUSerEntity(UserCreatedDto userCreatedDto);

    UserCreatedDto userEntityToUserCreatedDto(UserEntity userEntity);

    UserResponseDto userEntityToUserResponseDto(UserEntity userEntity);

    void updateEntityFromDto(UserCreatedDto dto, @MappingTarget UserEntity entity);
}
