package com.acc.hotelmanagement.service;

import com.acc.hotelmanagement.dto.BookingDTO;
import com.acc.hotelmanagement.model.Booking;
import com.acc.hotelmanagement.model.Room;

import java.util.List;

public interface BookingService {

    List<Booking> getAllBookings();

    Booking getBooking(Long id);

    Booking createBooking(Room room, BookingDTO bookingDTO);

    void deleteBooking(Booking booking);
}
