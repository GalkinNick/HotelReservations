package com.example.HotelReservations.DTO.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreatedDto {

    private String name;

    private String password;

    private String email;

    private String role;
}
