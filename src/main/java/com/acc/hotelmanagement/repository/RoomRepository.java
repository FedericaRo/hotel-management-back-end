package com.acc.hotelmanagement.repository;

import com.acc.hotelmanagement.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoomRepository extends JpaRepository<Room, Long>, JpaSpecificationExecutor<Room> {
//    List<Room> findByNumberOfGuests(Integer nGuests);
}
