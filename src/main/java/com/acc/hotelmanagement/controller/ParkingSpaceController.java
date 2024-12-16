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

    /**
     * Adds a parking space to an existing booking
     *
     * @param bookingId the ID of the booking to add the parking space to
     * @return the booking with the parking space added
     */
    @PutMapping("addParking/{bookingId}")
    public BookingDTO addParkingSpace(@PathVariable Long bookingId) {
        return bookingHandlerService.assignParkingSpaceToExistingBooking(bookingId);
    }

    /**
     * Removes a parking space from an existing booking
     *
     * @param bookingId the ID of the booking to remove the parking space from
     * @return the booking with the parking space removed
     */
    @PutMapping("removeParking/{bookingId}")
    public BookingDTO removeParkingSpace(@PathVariable Long bookingId) {
        return bookingHandlerService.removeParkingSpaceToExistingBooking(bookingId);
    }

}
