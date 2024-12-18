package com.acc.hotelmanagement.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class RoomFilterDTO {

    private Integer numberOfGuests;
    private String type;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
}
