package com.example.HotelReservations.specifications;

import com.example.HotelReservations.entity.HotelEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class HotelSpecifications {

    public static Specification<HotelEntity> hasId(UUID id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
    }

    public static Specification<HotelEntity> hasName(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<HotelEntity> hasAdTitle(String adTitle) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("adTitle")), "%" + adTitle.toLowerCase() + "%");
    }

    public static Specification<HotelEntity> hasCity(String city) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("city")), "%" + city.toLowerCase() + "%");
    }

    public static Specification<HotelEntity> hasAddress(String address) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("address")), "%" + address.toLowerCase() + "%");
    }

    public static Specification<HotelEntity> hasDistanceToCenter(float distanceToCenter) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("distanceToCenter"), distanceToCenter);
    }

    public static Specification<HotelEntity> hasRating(double rating) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("rating"), rating);
    }

    public static Specification<HotelEntity> hasRatingCount(int ratingCount) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("ratingCount"), ratingCount);
    }
}
