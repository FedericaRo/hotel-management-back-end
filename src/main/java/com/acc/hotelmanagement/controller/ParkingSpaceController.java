package com.acc.hotelmanagement.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acc.hotelmanagement.dto.BookingDTO;
import com.acc.hotelmanagement.service.ParkingSpaceService;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/parking")
@AllArgsConstructor
public class ParkingSpaceController {

    private final ParkingSpaceService parkingSpaceService;

    @PutMapping("reserveParking/{bookingId}")
    public BookingDTO addParkingSpace(@PathVariable Long bookingId) {
        
        return parkingSpaceService.reserveParkingSpace(bookingId);
    }

    @PutMapping("removeParking/{bookingId}")
    public BookingDTO removeParkingSpace(@PathVariable Long bookingId) {
        return parkingSpaceService.reserveParkingSpace(bookingId);
    }

}
