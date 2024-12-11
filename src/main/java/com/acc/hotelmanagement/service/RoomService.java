package com.acc.hotelmanagement.service;

import com.acc.hotelmanagement.model.Room;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RoomService {
    List<Room> getAllRooms();
}
