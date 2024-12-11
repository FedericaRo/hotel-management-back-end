package com.acc.hotelmanagement.controller;

import com.acc.hotelmanagement.model.Booking;
import com.acc.hotelmanagement.repository.BookingRepository;
import com.acc.hotelmanagement.service.BookingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }


    @GetMapping
    public List<Booking> getAllBookings()
    {
        return bookingService.getAllBookings();
    }
}
