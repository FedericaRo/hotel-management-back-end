package com.acc.hotelmanagement.service;

import com.acc.hotelmanagement.dto.BookingDTO;

import java.util.List;

/**
 * Interface for managing bookings and their associated parking spaces.
 * This service acts as an abstraction layer that coordinates between booking and parking-related operations.
 * Key responsibilities include:
 * - Handling CRUD operations for bookings.
 * - Managing optional parking space allocation for bookings.
 * - Providing detailed information about bookings and their associated parking spaces.
 * - Ensuring consistent release of resources, such as parking spaces, when bookings are deleted or modified.
 */
public interface BookingHandlerService {

    List<BookingDTO> getAllBookingsWithDetails();

    BookingDTO createBookingWithOptionalParking(Long roomId, BookingDTO bookingDTO);

//    BookingDTO createBooking(BookingDTO bookingDTO);

    void deleteBookingAndReleaseParking(Long bookingId);

    BookingDTO getBookingDetails(Long bookingId);

    BookingDTO assignParkingSpaceToExistingBooking(Long bookingId);

    BookingDTO removeParkingSpaceToExistingBooking(Long bookingId);


}
