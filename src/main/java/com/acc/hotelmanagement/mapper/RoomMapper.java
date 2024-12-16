package com.acc.hotelmanagement.mapper;

import com.acc.hotelmanagement.dto.RoomDTO;
import com.acc.hotelmanagement.model.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Maps between Room entities and RoomDTOs
 */
@Mapper(componentModel = "spring")
public interface RoomMapper {

    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    RoomDTO toDTO(Room room);

    @Mapping(target = "bookings", ignore = true)
    Room toEntity(RoomDTO roomDTO);

}
