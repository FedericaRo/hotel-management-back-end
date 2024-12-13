package com.acc.hotelmanagement.controller;

import com.acc.hotelmanagement.dto.BookingDTO;
import com.acc.hotelmanagement.service.BookingHandlerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/parking")
@AllArgsConstructor
public class ParkingSpaceController {

    private final BookingHandlerService bookingHandlerService;

    @PutMapping("reserveParking/{bookingId}")
    public BookingDTO addParkingSpace(@PathVariable Long bookingId) {
        return bookingHandlerService.assignParkingSpaceToExistingBooking(bookingId);
    }

    @PutMapping("removeParking/{bookingId}")
    public BookingDTO removeParkingSpace(@PathVariable Long bookingId) {
        return bookingHandlerService.removeParkingSpaceToExistingBooking(bookingId);
    }

}
