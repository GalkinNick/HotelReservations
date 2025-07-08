package com.example.HotelReservations.service;

import com.example.HotelReservations.entity.Statistic;
import java.util.List;

public interface StatisticService {

    void saveEvent(String eventType, Object eventData);

    List<Statistic> getAllStatistics();
}
