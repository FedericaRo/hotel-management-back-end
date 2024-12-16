package com.acc.hotelmanagement.mapper.service_mapper;

import com.acc.hotelmanagement.dto.RoomDTO;
import com.acc.hotelmanagement.mapper.RoomMapper;
import com.acc.hotelmanagement.model.Room;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class that provides methods to map between Room entities and RoomDTOs.
 */
@Service
public class RoomMapperService {

    private final RoomMapper mapper = RoomMapper.INSTANCE;

    public Room toEntity(RoomDTO dto) {
        return mapper.toEntity(dto);
    }

    public List<Room> toEntity(List<RoomDTO> dtos) {
        return dtos.stream()
                .map(mapper::toEntity)
                .toList();
    }

    public RoomDTO toDTO(Room entity) {
        return mapper.toDTO(entity);
    }

    public List<RoomDTO> toDTO(List<Room> entities) {
        return entities.stream()
                .map(mapper::toDTO)
                .toList();
    }
}
