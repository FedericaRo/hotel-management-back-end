package com.acc.hotelmanagement.repository;

import com.acc.hotelmanagement.model.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, Long> {

    /**
     * Finds a list of available parking spaces, if present.
     *
     * @return a list of parking spaces
     */
    List<ParkingSpace> findByAssignedFalse();

    // * LIMIT is not a feature of JPQL
    // @Query(value = "SELECT * FROM parking_spaces p WHERE p.assigned = false LIMIT 1", nativeQuery = true)
    // Optional<ParkingSpace> findAvailableParking();
}
