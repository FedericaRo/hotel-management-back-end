package com.acc.hotelmanagement.service;

import com.acc.hotelmanagement.model.Booking;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BookingService {

    public List<Booking> getAllBookings();
    public Booking createNewBooking();
}
