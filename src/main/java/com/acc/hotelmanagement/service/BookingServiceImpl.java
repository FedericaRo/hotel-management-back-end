package com.acc.hotelmanagement.service;

import com.acc.hotelmanagement.dto.BookingDTO;
import com.acc.hotelmanagement.exception.InvalidBookingDateException;
import com.acc.hotelmanagement.exception.RoomNotAvailableException;
import com.acc.hotelmanagement.mapper.service_mapper.BookingMapperService;
import com.acc.hotelmanagement.mapper.service_mapper.RoomMapperService;
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
    private final RoomService roomService;
    private final RoomMapperService roomMapperService;

    @Override
    public List<Booking> getAllBookings() {
        System.out.println("Getting all bookings...");
        return bookingRepository.findAll();
    }

    @Override
    public Booking createBooking(Room room, BookingDTO bookingDTO) {

        areDatesValid(bookingDTO.getCheckInDate(), bookingDTO.getCheckOutDate());

        if (!isRoomAvailable(room.getId(), bookingDTO.getCheckInDate(), bookingDTO.getCheckOutDate()))
            throw new RoomNotAvailableException("The room is not available for the dates " + bookingDTO.getCheckInDate() + ", " + bookingDTO.getCheckOutDate());

        Booking booking = bookingMapperService.toEntity(bookingDTO);
        System.out.println("Booking entity from before adding Room " + booking);
        booking.setRoom(room);

        System.out.println("Booking entity from DTO after adding Room " + booking);
        Booking savedBooking = bookingRepository.save(booking);
        System.out.println("Booking entity AFTER SAVING " + savedBooking);

        return savedBooking;
    }

    @Override
    public void deleteBooking(Booking booking) {
        bookingRepository.delete(booking);
    }

    private boolean isRoomAvailable(Long roomId, LocalDate checkInDate, LocalDate checkOutDate) {
        return bookingRepository.findOverlappingBookings(roomId, checkInDate, checkOutDate).isEmpty();
    }

    private void areDatesValid(LocalDate checkInDate, LocalDate checkOutDate) {
        if (!isCheckInDateLaterThanToday(checkInDate))
            throw new InvalidBookingDateException("Check-in date must be in the future");

        if (!isCheckOutLaterThanCheckIn(checkInDate, checkOutDate))
            throw new InvalidBookingDateException("Check-out date must be after the check-in date");
    }

    private boolean isCheckInDateLaterThanToday(LocalDate checkInDate) {
        return !checkInDate.isBefore(LocalDate.now());
    }

    private boolean isCheckOutLaterThanCheckIn(LocalDate checkInDate, LocalDate checkOutDate) {
        return checkInDate.isBefore(checkOutDate);
    }

    @Override
    public Booking getBooking(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking with ID " + id + " not found"));
    }

}
