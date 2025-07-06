package com.example.HotelReservations.specifications;

import com.example.HotelReservations.entity.BookingEntity;
import com.example.HotelReservations.entity.RoomEntity;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;



import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Subquery;

public class RoomSpecifications {

    public static Specification<RoomEntity> hasId(UUID id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
    }

    public static Specification<RoomEntity> hasDescription(String description) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%" + description.toLowerCase() + "%");
    }

    public static Specification<RoomEntity> hasPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (minPrice != null) {
                predicates.add((Predicate) criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice));
            }
            if (maxPrice != null) {
                predicates.add((Predicate) criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice));
            }


           // return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            return null;
        };
    }

    public static Specification<RoomEntity> hasGuests(Integer guests) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("guests"), guests);
    }

    public static Specification<RoomEntity> hasHotelId(Long hotelId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("hotel").get("id"), hotelId); // Доступ через связь
    }

    public static Specification<RoomEntity> isAvailableBetween(LocalDate checkIn, LocalDate checkOut) {
        if (checkIn == null || checkOut == null) {
            return null;
        }
        return (root, query, criteriaBuilder) -> {
            javax.persistence.criteria.Subquery<Long> subquery = (Subquery<Long>) query.subquery(Long.class);
            javax.persistence.criteria.Root<BookingEntity> bookingRoot = subquery.from(BookingEntity.class);
            subquery.select(bookingRoot.get("room").get("id"));

            /*Predicate overlapCondition = criteriaBuilder.and(
                    criteriaBuilder.lessThan(bookingRoot.get("checkIn"), checkOut),
                    criteriaBuilder.greaterThan(bookingRoot.get("checkOut"), checkIn)
            );
            subquery.where(overlapCondition);*/

            return criteriaBuilder.not(root.get("id").in(subquery));
        };
    }
}
