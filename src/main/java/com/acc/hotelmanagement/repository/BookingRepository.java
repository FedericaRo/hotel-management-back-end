package com.acc.hotelmanagement.repository;

import com.acc.hotelmanagement.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

// public List<Booking> findByRoomIdAndCheckInDateAfterAndCheckOutDateBefore(Long roomId, LocalDate checkInDate, LocalDate checkOutDate);
// public List<Booking> findByRoomIdAndCheckInDateLessThanAndCheckOutDateGreaterThan(Long roomId, LocalDate checkOutDate, LocalDate checkInDate);

    @Query("SELECT b FROM Booking b WHERE b.room.id = :roomId AND " +
            "b.checkInDate < :checkOutDate AND b.checkOutDate > :checkInDate")
    List<Booking> findOverlappingBookings(@Param("roomId") Long roomId,
                                          @Param("checkInDate") LocalDate checkInDate,
                                          @Param("checkOutDate") LocalDate checkOutDate);


}
