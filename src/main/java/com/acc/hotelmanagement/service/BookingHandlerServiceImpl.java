package com.acc.hotelmanagement.service;

import com.acc.hotelmanagement.dto.BookingDTO;
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
public class BookingHandlerServiceImpl implements BookingHandlerService{

    private final BookingService bookingService;
    private final BookingMapperService bookingMapperService;
    private final RoomService roomService;
    private final ParkingSpaceService parkingSpaceService;

    @Override
    public List<BookingDTO> getAllBookingsWithDetails() {
        List<Booking> bookings = bookingService.getAllBookings();
        return bookingMapperService.toDTO(bookings);
    }

    @Override
    public BookingDTO createBookingWithOptionalParking(Long roomId, BookingDTO bookingDTO) {

        // Get the room to add the booking to, if it exists
        Room room = roomService.getRoom(roomId);

        // create a new booking
        Booking newBooking = bookingService.createBooking(room, bookingDTO);

        // Assign a parking space to the booking
        if (bookingDTO.isReservedParking())
            newBooking = assignParkingSpace(newBooking);

        return bookingMapperService.toDTO(newBooking);
    }

    private Booking assignParkingSpace(Booking booking)
    {
        // TODO Change to a more specific exception
        if(booking.getParkingSpace() != null)
            throw new RuntimeException("This booking already has a parking space assigned");

        ParkingSpace parkingSpace = parkingSpaceService.reserveParkingSpace();
        booking.setParkingSpace(parkingSpace);
        return booking;

    }

    public BookingDTO assignParkingSpaceToExistingBooking(Long bookingId)
    {
        Booking existingBooking = bookingService.getBooking(bookingId);
        assignParkingSpace(existingBooking);
        return bookingMapperService.toDTO(existingBooking);
    }

    @Override
    public BookingDTO removeParkingSpaceToExistingBooking(Long bookingId) {
        Booking existingBooking = bookingService.getBooking(bookingId);
        ParkingSpace parkingSpace = existingBooking.getParkingSpace();
        if (parkingSpace == null)
            throw new RuntimeException("This booking does not have a reserved parking spot");

        parkingSpaceService.removeParkingSpace(parkingSpace.getId());
        existingBooking.setParkingSpace(null);

        return bookingMapperService.toDTO(existingBooking);

    }

    @Override
    public void deleteBookingAndReleaseParking(Long bookingId) {

        Booking booking = bookingService.getBooking(bookingId);

        if (booking.getParkingSpace() != null)
            parkingSpaceService.removeParkingSpace(booking.getParkingSpace().getId());

        bookingService.deleteBooking(booking);

    }

    @Override
    public BookingDTO getBookingDetails(Long bookingId) {
        return bookingMapperService.toDTO(bookingService.getBooking(bookingId));
    }

}
