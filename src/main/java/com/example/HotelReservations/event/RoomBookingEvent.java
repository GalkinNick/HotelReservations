package com.example.HotelReservations.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomBookingEvent {


    private UUID roomId;
    private UUID userId;
    private LocalDate startDate;
    private LocalDate endDate;
    //private String userId;
    //private long checkInData;
    //private long checkOutData;



}
