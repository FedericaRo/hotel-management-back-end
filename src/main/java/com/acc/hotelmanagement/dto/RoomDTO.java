package com.acc.hotelmanagement.dto;

import com.acc.hotelmanagement.model.RoomType;
import com.acc.hotelmanagement.validation.ValidRoomType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RoomDTO {

    private Long id;

    @NotBlank
    @ValidRoomType
    private RoomType type;

    @NotBlank
    @Min(value = 1, message = "The number of guests must be at least 1")
    private int numberOfGuests;

    @NotBlank
    @Min(value = 0, message = "The price must be at least 0")
    private double price;

    public RoomDTO(){}


}
