package com.acc.hotelmanagement.service;

import com.acc.hotelmanagement.dto.BookingDTO;

public interface ParkingSpaceService {

    public BookingDTO reserveParkingSpace(Long bookingId);
    public BookingDTO removeParkingSpace(Long bookingId);

}
