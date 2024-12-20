package com.acc.hotelmanagement.mapper;

import com.acc.hotelmanagement.dto.BookingDTO;
import com.acc.hotelmanagement.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Maps between Booking entities and BookingDTOs
 */
@Mapper(componentModel = "spring")
public interface BookingMapper {

    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    @Mapping(source = "room.id", target = "roomId")
    @Mapping(source = "room.type", target = "roomType")
    @Mapping(source = "parkingSpace.code", target = "parkingCode")
    @Mapping(source = "parkingSpace.assigned", target = "reservedParking")
    BookingDTO toDTO(Booking booking);

    @Mapping(source = "roomId", target = "room.id")
    @Mapping(target = "room", ignore = true)
    @Mapping(target = "parkingSpace", ignore = true)
    Booking toEntity(BookingDTO bookingDTO);
}
