package com.itmdexam.booking.repository;

import com.itmdexam.booking.entity.Booking;
import com.itmdexam.booking.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("""
        SELECT COUNT(b) > 0
        FROM Booking b
        WHERE b.room = :room
        AND b.bookingDate = :bookingDate
        AND b.startTime < :endTime
        AND b.endTime > :startTime
    """)
    boolean existsOverlappingBooking(
            @Param("room") Room room,
            @Param("bookingDate") LocalDate bookingDate,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime
    );

    @Query("""
        SELECT COUNT(b) > 0
        FROM Booking b
        WHERE b.room = :room
        AND b.bookingDate = :bookingDate
        AND b.startTime < :endTime
        AND b.endTime > :startTime
        AND b.id <> :bookingId
    """)
    boolean existsOverlappingBookingForUpdate(
            @Param("room") Room room,
            @Param("bookingDate") LocalDate bookingDate,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime,
            @Param("bookingId") Long bookingId
    );
}