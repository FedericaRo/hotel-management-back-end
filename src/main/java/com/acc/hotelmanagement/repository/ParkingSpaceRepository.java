package com.acc.hotelmanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.acc.hotelmanagement.model.ParkingSpace;

public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, Long> {

    List<ParkingSpace> findByAssignedFalse();

    // * LIMIT is not a feature of JPQL
    // @Query(value = "SELECT * FROM parking_spaces p WHERE p.assigned = false LIMIT 1", nativeQuery = true)
    // Optional<ParkingSpace> findAvailableParking();


}
