package com.acc.hotelmanagement.service;

import com.acc.hotelmanagement.dto.RoomDTO;
import com.acc.hotelmanagement.model.Room;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    List<RoomDTO> getAllRooms();
    RoomDTO getOneRoom(Long roomId);
}
