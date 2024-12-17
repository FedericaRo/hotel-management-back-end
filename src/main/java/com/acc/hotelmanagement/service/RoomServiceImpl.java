package com.acc.hotelmanagement.service;

import com.acc.hotelmanagement.dto.RoomDTO;
import com.acc.hotelmanagement.dto.RoomFilterDTO;
import com.acc.hotelmanagement.mapper.service_mapper.RoomMapperService;
import com.acc.hotelmanagement.model.Room;
import com.acc.hotelmanagement.repository.RoomRepository;
import com.acc.hotelmanagement.specification.RoomSpecification;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapperService roomMapperService;

    public RoomServiceImpl(RoomRepository roomRepository, RoomMapperService roomMapperService) {
        this.roomRepository = roomRepository;
        this.roomMapperService = roomMapperService;
    }

    // Retrieves all rooms and maps them to RoomDTO
    @Override
    public List<RoomDTO> getAllRooms() {
        System.out.println("Getting all rooms...");
        List<Room> rooms = roomRepository.findAll();
        return roomMapperService.toDTO(rooms);
    }

    // Retrieves a room DTO by its ID
    @Override
    public RoomDTO getRoomDTO(Long roomId) {
        return roomMapperService.toDTO(this.getRoomById(roomId));
    }
    // Retrieves a room entity by its ID
    @Override
    public Room getRoomById(Long roomId) {
        return roomRepository.findById(roomId)
                .orElseThrow(() -> new EntityNotFoundException("Room with ID " + roomId + " not found"));
    }


    // Creates a new room
    @Override
    public RoomDTO createRoom(RoomDTO roomDTO) {
        Room newRoom = roomMapperService.toEntity(roomDTO);
        return roomMapperService.toDTO(roomRepository.save(newRoom));
    }

    // Retrieves all rooms that match the specified filter criteria
    public List<RoomDTO> getFilteredRooms(RoomFilterDTO filterDTO)
    {
        Specification<Room> spec = Specification
                .where(RoomSpecification.withNumberOfGuests(filterDTO.getNumberOfGuests()))
                .and(RoomSpecification.withRoomType(filterDTO.getType()))
                .and(RoomSpecification.isAvailableBetween(filterDTO.getCheckInDate(), filterDTO.getCheckOutDate()));

        return roomMapperService.toDTO(roomRepository.findAll(spec));
    }

    /*@Override
    public Room getRoomByNumberGuests(Integer numberOfGuests) {
        return roomRepository.findByNumberOfGuests(numberOfGuests).stream().findFirst()
                            .orElseThrow(() -> new EntityNotFoundException("There are no room available for " + numberOfGuests+ " guests" ));
    }*/


}
