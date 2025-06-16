package com.example.HotelReservations.error;



public class EntityNotFoundException extends RuntimeException{


    public EntityNotFoundException(String message){
        super(message);
    }
}


