package com.example.HotelReservations.service;

import com.example.HotelReservations.event.RoomBookingEvent;
import com.example.HotelReservations.event.UserRegistrationEvent;

public interface EventProducerService {

    void sendUserRegistrationEvent(UserRegistrationEvent event);

    void sendRoomBookingEvent(RoomBookingEvent event);

}
