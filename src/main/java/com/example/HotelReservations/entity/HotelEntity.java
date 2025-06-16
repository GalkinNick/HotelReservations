package com.example.HotelReservations.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@Entity
@Table(name = "hotels")
public class HotelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "ad_title")
    private String adTitle;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "address")
    private String address;

    @Column(name = "distance_from_center")
    private float distanceFromCenter;

    @Column(name = "rating")
    private int rating;

    @Column(name = "rating_count")
    private int ratingCount;
}
