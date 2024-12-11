package com.acc.hotelmanagement.mapper;
import com.acc.hotelmanagement.dto.BookingDTO;
import com.acc.hotelmanagement.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface BookingMapper {

    public static final BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    BookingDTO toDTO(Booking booking);

    Booking toEntity(BookingDTO bookingDTO);
}
