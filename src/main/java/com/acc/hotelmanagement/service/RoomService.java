package com.acc.hotelmanagement.service;

import com.acc.hotelmanagement.dto.RoomDTO;
import com.acc.hotelmanagement.dto.RoomFilterDTO;
import com.acc.hotelmanagement.model.Room;

import java.util.List;


public interface RoomService {

    List<RoomDTO> getAllRooms();

    RoomDTO getRoomDTO(Long roomId);

    Room getRoomById(Long roomId);

    RoomDTO createRoom(RoomDTO roomDTO);

    List<RoomDTO> getFilteredRooms(RoomFilterDTO filterDTO);

//    Room getRoomByNumberGuests(Integer numberOfGuests);
}
