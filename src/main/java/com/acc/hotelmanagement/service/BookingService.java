package com.acc.hotelmanagement.service;

import java.util.List;

import com.acc.hotelmanagement.dto.BookingDTO;
import com.acc.hotelmanagement.model.Booking;

public interface BookingService {

    public List<BookingDTO> getAllBookings();
    // public Optional<BookingDTO> getOneBooking(Long id);
    public BookingDTO getOneBookingDTO(Long id);
    public Booking getOneBooking(Long id);
    public BookingDTO createNewBooking(Long roomId, BookingDTO bookingDTO);
    public Booking insert(Booking booking);
}
