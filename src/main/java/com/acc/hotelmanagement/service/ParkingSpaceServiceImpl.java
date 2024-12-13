package com.acc.hotelmanagement.service;

import com.acc.hotelmanagement.mapper.service_mapper.BookingMapperService;
import com.acc.hotelmanagement.model.ParkingSpace;
import com.acc.hotelmanagement.repository.ParkingSpaceRepository;
import jakarta.persistence.EntityNotFoundException;
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
    public ParkingSpace reserveParkingSpace() {

        ParkingSpace parkingSpace = findAvailableParkingSpace();
        parkingSpace.setAssigned(true);
        return parkingSpace;
    }

    @Override
    public void removeParkingSpace(Long parkingSpaceId) {

        ParkingSpace parkingSpace = parkingSpaceRepository.findById(parkingSpaceId)
                .orElseThrow(() -> new EntityNotFoundException("Parking space with id " + parkingSpaceId + " not found"));
        parkingSpace.setAssigned(false);

    }

    //  Find the first available parking space, if present
    private ParkingSpace findAvailableParkingSpace() {
        return parkingSpaceRepository.findByAssignedFalse()
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No available parking spaces."));
    }

}
