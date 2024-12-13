package com.acc.hotelmanagement.mapper.service_mapper;

import com.acc.hotelmanagement.dto.BookingDTO;
import com.acc.hotelmanagement.mapper.BookingMapper;
import com.acc.hotelmanagement.model.Booking;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingMapperService {

    private BookingMapper mapper = BookingMapper.INSTANCE;

    public Booking toEntity(BookingDTO dto) {
        return mapper.toEntity(dto);
    }

    public List<Booking> toEntity(List<BookingDTO> dtos) {
        return dtos.stream()
                .map(dto -> mapper.toEntity(dto))
                .toList();
    }

    public BookingDTO toDTO(Booking entity) {
        return mapper.toDTO(entity);
    }

    public List<BookingDTO> toDTO(List<Booking> entities) {
        return entities.stream()
                .map(entity -> mapper.toDTO(entity))
                .toList();
    }
}
