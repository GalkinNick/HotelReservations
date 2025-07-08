package com.example.HotelReservations.controller;

import com.example.HotelReservations.service.CsvExportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
@Slf4j
public class StatisticsController {

    private final CsvExportService csvExportService;

    @GetMapping("/export-csv")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> exportStatisticsToCsv() {
        try {
            String csvData = csvExportService.exportToCsv();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("text/csv"));
            headers.setContentDispositionFormData("attachment", "statistics.csv");

            return new ResponseEntity<>(csvData, headers, HttpStatus.OK);

        } catch (Exception e) {
            log.error("Error exporting statistics to CSV: ", e);
            return new ResponseEntity<>("Error generating CSV file.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
