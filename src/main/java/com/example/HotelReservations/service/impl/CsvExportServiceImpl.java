package com.example.HotelReservations.service.impl;

import com.example.HotelReservations.entity.Statistic;
import com.example.HotelReservations.service.CsvExportService;
import com.example.HotelReservations.service.StatisticService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.springframework.stereotype.Service;

import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CsvExportServiceImpl implements CsvExportService {

    private final StatisticService statisticService;
    private final ObjectMapper objectMapper;


    @Override
    public String exportToCsv() throws IOException {

        List<Statistic> statistics = statisticService.getAllStatistics();

        StringWriter sw = new StringWriter();
        CSVPrinter csvPrinter = new CSVPrinter(sw, CSVFormat.DEFAULT
                .withHeader("ID", "Event Type", "Room ID", "User ID", "Check-in Date", "Check-out Date", "Timestamp"));

        for (Statistic statistic : statistics){
            try{
                JsonNode eventData = objectMapper.readTree((JsonParser) statistic.getEventData());

                String roomId = null;
                String userId = null;
                String checkInDate = null;
                String checkOutDate = null;
                String timestamp = null;


                if (eventData.has("roomId")) {
                    roomId = eventData.get("roomId").asText();
                }
                if (eventData.has("userId")) {
                    userId = eventData.get("userId").asText();
                }
                if (eventData.has("checkInDate")) {
                    checkInDate = eventData.get("checkInDate").asText();
                }
                if (eventData.has("checkOutDate")) {
                    checkOutDate = eventData.get("checkOutDate").asText();
                }
                if (eventData.has("timestamp")) {
                    timestamp = eventData.get("timestamp").asText();
                }

                csvPrinter.printRecord(
                        statistic.getId(),
                        statistic.getEventType(),
                        roomId,
                        userId,
                        checkInDate,
                        checkOutDate,
                        timestamp
                );
            }
            catch (Exception ex) {
                log.error("Error processing statistics for CSV ", ex);
            }
        }

        csvPrinter.flush();
        return sw.toString();
    }
}
