package com.example.HotelReservations.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "statistics")
public class Statistic {

    @Id
    private String id;

    private String eventType;

    private Object eventData;

}
