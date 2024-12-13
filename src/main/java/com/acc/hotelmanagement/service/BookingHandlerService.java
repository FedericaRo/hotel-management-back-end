package com.acc.hotelmanagement.service;

import com.acc.hotelmanagement.dto.BookingDTO;

import java.util.List;

//Service to manage booking with parking
public interface BookingHandlerService {

    List<BookingDTO> getAllBookingsWithDetails();

    BookingDTO createBookingWithOptionalParking(Long roomId, BookingDTO bookingDTO);

    void deleteBookingAndReleaseParking(Long bookingId);

    BookingDTO getBookingDetails(Long bookingId);

    BookingDTO assignParkingSpaceToExistingBooking(Long bookingId);

    BookingDTO removeParkingSpaceToExistingBooking(Long bookingId);


}
