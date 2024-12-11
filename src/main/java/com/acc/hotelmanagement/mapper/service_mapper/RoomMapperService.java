package com.acc.hotelmanagement.mapper.service_mapper;

import java.util.List;

import com.acc.hotelmanagement.dto.RoomDTO;
import com.acc.hotelmanagement.mapper.RoomMapper;
import com.acc.hotelmanagement.model.Room;

public class RoomMapperService {

    private RoomMapper mapper = RoomMapper.INSTANCE;

    public Room toEntity(RoomDTO dto)
    {
        return mapper.toEntity(dto);
    }

    public List<Room> toEntity(List<RoomDTO> dtos)
    {
        return dtos.stream()
                    .map(dto -> mapper.toEntity(dto))
                    .toList();
    }

    public RoomDTO toDTO(Room entity)
    {
        return mapper.toDTO(entity);
    }

    public List<RoomDTO> toDTO(List<Room> entities)
    {
        return entities.stream()
                .map(entity -> mapper.toDTO(entity))
                .toList();
    }
}
