package com.acc.hotelmanagement.service;

import com.acc.hotelmanagement.dto.BookingDTO;
import com.acc.hotelmanagement.exception.ParkingSpaceException;
import com.acc.hotelmanagement.mapper.service_mapper.BookingMapperService;
import com.acc.hotelmanagement.model.Booking;
import com.acc.hotelmanagement.model.ParkingSpace;
import com.acc.hotelmanagement.model.Room;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class BookingHandlerServiceImpl implements BookingHandlerService {

    private final BookingService bookingService;
    private final BookingMapperService bookingMapperService;
    private final RoomService roomService;
    private final ParkingSpaceService parkingSpaceService;

    // Retrieves all bookings with their detailed information.
    @Override
    public List<BookingDTO> getAllBookingsWithDetails() {
        List<Booking> bookings = bookingService.getAllBookings();
        return bookingMapperService.toDTO(bookings);
    }

//    @Override
//    public BookingDTO createBooking(BookingDTO bookingDTO) {
//
//        Room room = roomService.getRoomByNumberGuests(bookingDTO.getNumberOfGuests());
//    }

    // Creates a new booking
    @Override
    public BookingDTO createBookingWithOptionalParking(Long roomId, BookingDTO bookingDTO) {

        // Get the room to add the booking to, if it exists
        Room room = roomService.getRoomById(roomId);

        // create a new booking
        Booking newBooking = bookingService.createBooking(room, bookingDTO);

        //  if requested, assign a parking space to the booking
        if (bookingDTO.isReservedParking())
            newBooking = assignParkingSpace(newBooking);

        return bookingMapperService.toDTO(newBooking);
    }


    // Assigns a parking space to a booking
    private Booking assignParkingSpace(Booking booking) {
        // Checks if the booking already has a reserved parking space
        if (booking.getParkingSpace() != null)
            throw new ParkingSpaceException("This booking already has a parking space assigned");

        // Gets the first available parking space
        ParkingSpace parkingSpace = parkingSpaceService.reserveParkingSpace();
        // Assigns it to the booking
        booking.setParkingSpace(parkingSpace);
        return booking;

    }

    // Assigns a parking space to an existing booking
    public BookingDTO assignParkingSpaceToExistingBooking(Long bookingId) {
        Booking existingBooking = getBooking(bookingId);
        assignParkingSpace(existingBooking);
        return bookingMapperService.toDTO(existingBooking);
    }


    // Removes a parking space from an existing booking
    @Override
    public BookingDTO removeParkingSpaceToExistingBooking(Long bookingId) {

        Booking existingBooking = getBooking(bookingId);
        ParkingSpace parkingSpace = existingBooking.getParkingSpace();

        if (parkingSpace == null)
            throw new ParkingSpaceException("This booking does not have a reserved parking spot");

        parkingSpaceService.removeParkingSpace(parkingSpace.getId());
        existingBooking.setParkingSpace(null);

        return bookingMapperService.toDTO(existingBooking);

    }

    // Deletes a booking and releases its assigned parking space
    @Override
    public void deleteBookingAndReleaseParking(Long bookingId) {

        Booking booking = getBooking(bookingId);

        // Releases the assigned parking space if it exists
        if (booking.getParkingSpace() != null)
            parkingSpaceService.removeParkingSpace(booking.getParkingSpace().getId());

        bookingService.deleteBooking(booking);
    }

    // Retrieves the details of a specific booking
    @Override
    public BookingDTO getBookingDetails(Long bookingId) {
        return bookingMapperService.toDTO(getBooking(bookingId));
    }

    // Retrieves a booking by its ID
    private Booking getBooking(Long bookingId) {
        return bookingService.getBooking(bookingId);
    }
}
