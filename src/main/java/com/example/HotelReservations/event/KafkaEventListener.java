package com.example.HotelReservations.event;

import com.example.HotelReservations.service.StatisticService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaEventListener {

    private final StatisticService statisticServiceImpl;

    @KafkaListener(topics = "${kafka.topic.user-registration}", groupId = "statistics-service-group")
    public void listenUserRegistration(UserRegistrationEvent event) {
        log.info("Received UserRegistrationEvent: {}", event);
        statisticServiceImpl.saveEvent("UserRegistration", event);
    }

    @KafkaListener(topics = "${kafka.topic.room-booking}", groupId = "statistics-service-group")
    public void listenRoomBooking(RoomBookingEvent event) {
        log.info("Received RoomBookingEvent: {}", event);
        statisticServiceImpl.saveEvent("RoomBooking", event);
    }

}
