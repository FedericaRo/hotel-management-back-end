package com.acc.hotelmanagement.controller;

import com.acc.hotelmanagement.dto.BookingDTO;
import com.acc.hotelmanagement.model.Booking;
import com.acc.hotelmanagement.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }


    @GetMapping
    public ResponseEntity<List<BookingDTO>> getAllBookings()
    {
        List<BookingDTO> bookingDTOs = bookingService.getAllBookings();
        return new ResponseEntity<>(bookingDTOs, HttpStatus.OK);
    }

    @PostMapping("/{roomId}")
    public ResponseEntity<BookingDTO> createNewBooking(@PathVariable long roomId, @Valid @RequestBody BookingDTO bookingDTO)
    {
        return new ResponseEntity<>(bookingService.createNewBooking(roomId, bookingDTO), HttpStatus.CREATED);
    }

}
