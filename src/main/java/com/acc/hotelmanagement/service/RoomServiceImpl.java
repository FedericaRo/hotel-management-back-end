package com.acc.hotelmanagement.service;

import com.acc.hotelmanagement.model.Room;
import com.acc.hotelmanagement.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<Room> getAllRooms() {
        System.out.println(roomRepository.findAll());
        return roomRepository.findAll();
    }
}
