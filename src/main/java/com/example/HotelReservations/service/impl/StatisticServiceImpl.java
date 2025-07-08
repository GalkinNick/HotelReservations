package com.example.HotelReservations.service.impl;

import com.example.HotelReservations.entity.Statistic;
import com.example.HotelReservations.repository.StatisticRepository;
import com.example.HotelReservations.service.StatisticService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticServiceImpl implements StatisticService {

    private final StatisticRepository statisticRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void saveEvent(String eventType, Object eventData) {
        try{
            Statistic statistic = new Statistic();
            statistic.setEventType(eventType);
            statistic.setEventData(objectMapper.writeValueAsString(eventData));

            statisticRepository.save(statistic);
        }
        catch (Exception ex){
            log.error("Error saving event to MongoDB: ", ex);
        }
    }

    @Override
    public List<Statistic> getAllStatistics() {
        return statisticRepository.findAll();
    }
}
