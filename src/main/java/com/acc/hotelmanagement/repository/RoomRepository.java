package com.acc.hotelmanagement.repository;

import com.acc.hotelmanagement.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
//    List<Room> findByNumberOfGuests(Integer nGuests);
}
