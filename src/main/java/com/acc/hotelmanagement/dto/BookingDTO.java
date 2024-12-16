package com.acc.hotelmanagement.dto;

import com.acc.hotelmanagement.validation.ValidRoomType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class BookingDTO {

    private Long id;

    @NotNull(message = "Check-in date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkInDate;

    @NotNull(message = "Check-out date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkOutDate;

    @NotNull(message = "Insert the number of guests")
    private Integer numberOfGuests;

    @ValidRoomType
    private String roomType;

    private Long roomId;

    private String parkingCode;
    private boolean reservedParking;

}
