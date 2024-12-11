package com.acc.hotelmanagement.mapper;

import com.acc.hotelmanagement.dto.RoomDTO;
import com.acc.hotelmanagement.model.Room;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoomMapper {

    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    RoomDTO toDTO(Room room);

    Room toEntity(RoomDTO roomDTO);

}
