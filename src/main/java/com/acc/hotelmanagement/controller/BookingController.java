package com.acc.hotelmanagement.controller;

import com.acc.hotelmanagement.dto.BookingDTO;
import com.acc.hotelmanagement.service.BookingHandlerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@AllArgsConstructor
public class BookingController {

    private final BookingHandlerService bookingHandlerService;

    @GetMapping
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        List<BookingDTO> bookingDTOs = bookingHandlerService.getAllBookingsWithDetails();
        return new ResponseEntity<>(bookingDTOs, HttpStatus.OK);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDTO> getBooking(@PathVariable long bookingId) {
        BookingDTO bookingDTO = bookingHandlerService.getBookingDetails(bookingId);
        return new ResponseEntity<>(bookingDTO, HttpStatus.OK);
    }

    @PostMapping("/{roomId}")
    public ResponseEntity<BookingDTO> createNewBooking(@PathVariable long roomId, @Valid @RequestBody BookingDTO bookingDTO) {
        return new ResponseEntity<>(bookingHandlerService.createBookingWithOptionalParking(roomId, bookingDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<String> deleteBooking(@PathVariable long bookingId) {
        bookingHandlerService.deleteBookingAndReleaseParking(bookingId);
        return new ResponseEntity<>("Booking deleted successfully", HttpStatus.OK);
    }


}
