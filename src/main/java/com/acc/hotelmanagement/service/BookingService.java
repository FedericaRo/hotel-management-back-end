package com.acc.hotelmanagement.service;

import com.acc.hotelmanagement.dto.BookingDTO;
import com.acc.hotelmanagement.model.Booking;
import jakarta.validation.Valid;

import java.util.List;

public interface BookingService {

    public List<BookingDTO> getAllBookings();
    public BookingDTO createNewBooking(Long roomId, BookingDTO bookingDTO);
}
