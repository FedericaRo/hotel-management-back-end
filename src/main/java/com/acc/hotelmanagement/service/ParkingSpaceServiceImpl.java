package com.acc.hotelmanagement.service;

import com.acc.hotelmanagement.dto.BookingDTO;
import com.acc.hotelmanagement.mapper.service_mapper.BookingMapperService;
import com.acc.hotelmanagement.model.Booking;
import com.acc.hotelmanagement.model.ParkingSpace;
import com.acc.hotelmanagement.repository.ParkingSpaceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@AllArgsConstructor
public class ParkingSpaceServiceImpl implements ParkingSpaceService {

    private final BookingService bookingService;
    private final BookingMapperService bookingMapperService;
    private final ParkingSpaceRepository parkingSpaceRepository;

    @Override
    public BookingDTO addParkingSpace(Long bookingId) {

        Booking booking = bookingService.getOneBooking(bookingId);

        if (booking.getParkingSpace() != null)
            throw new RuntimeException("This booking already has a parking space assigned");
        // ! Change to a more specific exception

        ParkingSpace parkingSpace = findAvailableParkingSpace();


        booking.setParkingSpace(parkingSpace);
        parkingSpace.setAssigned(true);
//        transactional does the saving
//        parkingSpaceRepository.save(parkingSpace);
//        Booking savedBooking = bookingService.insert(booking);

        return bookingMapperService.toDTO(booking);

    }

    //  Find the first available parking space, if present
    private ParkingSpace findAvailableParkingSpace() {
        return parkingSpaceRepository.findByAssignedFalse()
            .stream()
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No available parking spaces."));
    }

    // // Find the first available parking space, if present
    // private ParkingSpace findAvailableParkingSpace() {
    //     List<ParkingSpace> availableParkingSpace = parkingSpaceRepository.findByAssignedFalse();

    //     if (availableParkingSpace.isEmpty())
    //         throw new RuntimeException("There are no available parking spaces");
    //     // ! Change to a more specific exception

    //     return availableParkingSpace.getFirst();
    // }

    
    

}
