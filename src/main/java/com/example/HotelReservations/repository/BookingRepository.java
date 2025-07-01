package com.example.HotelReservations.repository;

import com.example.HotelReservations.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, UUID> {

  //  @Query("SELECT COUNT(b) FROM booking b WHERE b.room.id = :roomId " +
  //          "AND ((b.startDate <= :endDate AND b.endDate >= :startDate))")
  //  long countOverlappingBookings(@Param("roomId") UUID roomId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    List<BookingEntity> findByUserId(UUID userId);
}
