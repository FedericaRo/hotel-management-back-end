package com.acc.hotelmanagement.service;

import com.acc.hotelmanagement.exception.ParkingSpaceException;
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

    private final ParkingSpaceRepository parkingSpaceRepository;

    // Reserve a parking space
    @Override
    public ParkingSpace reserveParkingSpace() {

        ParkingSpace parkingSpace = findAvailableParkingSpace();
        parkingSpace.setAssigned(true);
        return parkingSpace;
    }

    // Set a parking space as not assigned
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
                .orElseThrow(() -> new ParkingSpaceException("No available parking spaces"));
    }

}
