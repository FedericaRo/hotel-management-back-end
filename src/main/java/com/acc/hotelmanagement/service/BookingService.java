package com.acc.hotelmanagement.service;

import com.acc.hotelmanagement.dto.BookingDTO;
import com.acc.hotelmanagement.model.Booking;
import com.acc.hotelmanagement.model.Room;

import java.util.List;

public interface BookingService {

    public List<Booking> getAllBookings();

    public Booking getBooking(Long id);

    public Booking createBooking(Room room, BookingDTO bookingDTO);

    public void deleteBooking(Booking booking);
}
