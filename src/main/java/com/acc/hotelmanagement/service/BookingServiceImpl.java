package com.acc.hotelmanagement.service;

import com.acc.hotelmanagement.dto.BookingDTO;
import com.acc.hotelmanagement.exception.InvalidBookingDateException;
import com.acc.hotelmanagement.exception.RoomNotAvailableException;
import com.acc.hotelmanagement.mapper.service_mapper.BookingMapperService;
import com.acc.hotelmanagement.model.Booking;
import com.acc.hotelmanagement.model.Room;
import com.acc.hotelmanagement.repository.BookingRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapperService bookingMapperService;

    // Retrieve all bookings
    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Create a new booking for a room
    @Override
    public Booking createBooking(Room room, BookingDTO bookingDTO) {

        // Check if dates are valid
        validateDates(bookingDTO.getCheckInDate(), bookingDTO.getCheckOutDate());

        // Check if room is already reserved on the given dates
        if (!isRoomAvailable(room.getId(), bookingDTO.getCheckInDate(), bookingDTO.getCheckOutDate()))
            throw new RoomNotAvailableException("The room is not available for the dates " + bookingDTO.getCheckInDate() + ", " + bookingDTO.getCheckOutDate());

        // Check if room type and number of guests match the request dto
        validateRoomCriteria(room, bookingDTO);

        Booking booking = bookingMapperService.toEntity(bookingDTO);
        System.out.println("Booking entity from before adding Room " + booking);
        booking.setRoom(room);

        System.out.println("Booking entity from DTO after adding Room " + booking);
        Booking savedBooking = bookingRepository.save(booking);
        System.out.println("Booking entity AFTER SAVING " + savedBooking);

        return savedBooking;
    }

    // Delete a booking
    @Override
    public void deleteBooking(Booking booking) {
        bookingRepository.delete(booking);
    }

    private void validateRoomCriteria(Room room, BookingDTO bookingDTO) {

        // If room type is provided for booking, it must correspond to the room type
        if(bookingDTO.getRoomType() != null && !bookingDTO.getRoomType().equalsIgnoreCase(room.getType().toString()))
                throw new IllegalArgumentException("Room type mismatch");

        // Number of guests in the booking must not exceed the number of guests capacity of the room
        if (bookingDTO.getNumberOfGuests() > room.getNumberOfGuests())
                throw new IllegalArgumentException("Number of guests exceeds room capacity");
    }

    // Check if a room is available for a given date range
    private boolean isRoomAvailable(Long roomId, LocalDate checkInDate, LocalDate checkOutDate) {
        return bookingRepository.findOverlappingBookings(roomId, checkInDate, checkOutDate).isEmpty();
    }

    private void validateDates(LocalDate checkInDate, LocalDate checkOutDate) {
        // Check if check-in date is in the future
        if (checkInDate.isBefore(LocalDate.now()))
            throw new InvalidBookingDateException("Check-in date must be in the future");

        // Check if check-out date is after check-in date
        if (!checkInDate.isBefore(checkOutDate))
            throw new InvalidBookingDateException("Check-out date must be after the check-in date");
    }

    // Retrieve a booking by ID if it exists
    @Override
    public Booking getBooking(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking with ID " + id + " not found"));
    }

}
