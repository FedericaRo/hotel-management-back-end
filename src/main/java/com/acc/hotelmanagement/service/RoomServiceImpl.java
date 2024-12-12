package com.acc.hotelmanagement.service;

import com.acc.hotelmanagement.dto.RoomDTO;
import com.acc.hotelmanagement.mapper.service_mapper.RoomMapperService;
import com.acc.hotelmanagement.model.Room;
import com.acc.hotelmanagement.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapperService roomMapperService;

    public RoomServiceImpl(RoomRepository roomRepository, RoomMapperService roomMapperService) {
        this.roomRepository = roomRepository;
        this.roomMapperService = roomMapperService;
    }

    @Override
    public List<RoomDTO> getAllRooms() {
        System.out.println("Getting all rooms...");
        System.out.println(roomRepository.findAll());
        List<Room> rooms = roomRepository.findAll();
        return roomMapperService.toDTO(rooms);
    }

    @Override
    public RoomDTO getOneRoom(Long roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new EntityNotFoundException("Room with ID " + roomId + "not found"));
        return roomMapperService.toDTO(room);
    }


}
