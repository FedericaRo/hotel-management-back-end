package com.acc.hotelmanagement.service;

import com.acc.hotelmanagement.dto.BookingDTO;
import com.acc.hotelmanagement.dto.RoomDTO;
import com.acc.hotelmanagement.exception.InvalidBookingDateException;
import com.acc.hotelmanagement.exception.RoomNotAvailableException;
import com.acc.hotelmanagement.mapper.service_mapper.BookingMapperService;
import com.acc.hotelmanagement.mapper.service_mapper.RoomMapperService;
import com.acc.hotelmanagement.model.Booking;
import com.acc.hotelmanagement.repository.BookingRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapperService bookingMapperService;
    private final RoomService roomService;
    private final RoomMapperService roomMapperService;

    @Override
    public List<BookingDTO> getAllBookings() {
        System.out.println("Getting all bookings...");
        System.out.println(bookingRepository.findAll());
        List<Booking> bookings = bookingRepository.findAll();
        return bookingMapperService.toDTO(bookings);

    }

    @Override
    public BookingDTO getOneBookingDTO(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking with ID " + id + "not found"));
        return bookingMapperService.toDTO(booking);

    }

    @Override
    public BookingDTO createNewBooking(Long roomId, BookingDTO bookingDTO) {

        RoomDTO roomDTO = roomService.getOneRoom(roomId);

        // Add a check to see if the room is available in those dates
        // 1. create a SQL QUERY directly comparing the dates in some ways
        // 2. get all the bookings for that room, use a lambda on
        // 3. Creating the query directly with the dates, if the query gets back
        // something then the booking already exist in those dates, otherwise free to
        // book
        System.out.println("roomDTO!! " + roomDTO);

        areDatesValid(bookingDTO.getCheckInDate(), bookingDTO.getCheckOutDate());

        boolean available = isRoomAvailable(roomId, bookingDTO.getCheckInDate(), bookingDTO.getCheckOutDate());

        if (!available)
            throw new RoomNotAvailableException("ROOM NOT AVAILABLE FOR DATES");

        Booking booking = bookingMapperService.toEntity(bookingDTO);
        System.out.println("Booking entity from before adding Room " + booking);
        booking.setRoom(roomMapperService.toEntity(roomDTO));

        System.out.println("Booking entity from DTO after adding Room " + booking);
        Booking savedBooking = bookingRepository.save(booking);
        System.out.println("Booking entity AFTER SAVING " + savedBooking);
        BookingDTO savedDTO = bookingMapperService.toDTO(savedBooking);
        System.out.println("Booking DTO AFTER SAVING " + savedDTO);

        return savedDTO;
        // return
        // bookingMapperService.toDTO(bookingRepository.save(bookingMapperService.toEntity(bookingDTO)));
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
        System.out.println(checkInDate);
        System.out.println(checkOutDate);
        return checkInDate.isBefore(checkOutDate);
    }

    @Override
    public Booking insert(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Booking getOneBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking with ID " + id + "not found"));
        return booking;
    }

    // public BookingDTO createNewBookingWithoutPathVariable(BookingDTO bookingDTO)
    // {
    //
    // Room room = roomRepository.findById(bookingDTO.getRoomId())
    // .orElseThrow(() -> new EntityNotFoundException("Room with ID " +
    // bookingDTO.getRoomId() + "not found" ));
    //
    // Booking booking = bookingMapperService.toEntity(bookingDTO);
    // booking.setRoom(room);
    //
    // Booking savedBooking = bookingRepository.save(booking);
    // BookingDTO savedDTO = bookingMapperService.toDTO(savedBooking);
    //
    // return savedDTO;
    // }

}
