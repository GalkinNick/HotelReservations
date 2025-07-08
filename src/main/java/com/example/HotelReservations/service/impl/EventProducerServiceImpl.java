package com.example.HotelReservations.service.impl;

import com.example.HotelReservations.event.RoomBookingEvent;
import com.example.HotelReservations.event.UserRegistrationEvent;
import com.example.HotelReservations.service.EventProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventProducerServiceImpl implements EventProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("app.kafka.userRegistrationTopic")
    private String userRegistrationTopic;

    @Value("app.kafka.roomBookingTopic")
    private String roomBookingTopic;

    @Override
    public void sendUserRegistrationEvent(UserRegistrationEvent event) {
        kafkaTemplate.send(userRegistrationTopic, event);
        log.info("User registration event sent to Kafka: {}", event);
    }

    @Override
    public void sendRoomBookingEvent(RoomBookingEvent event) {
        kafkaTemplate.send(roomBookingTopic, event);
        log.info("Room booking event sent to Kafka: {}", event);
    }
}
