package com.acc.hotelmanagement.dto;

import com.acc.hotelmanagement.validation.ValidRoomType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RoomDTO {

    private Long id;

    @NotNull(message = "Room type cannot be null")
    @ValidRoomType
    private String type;

    @NotNull(message = "Number of guests field cannot be null")
    @Min(value = 1, message = "The number of guests must be at least 1")
    private Integer numberOfGuests;

    @NotNull(message = "Price field cannot be null")
    @Min(value = 0, message = "The price must be at least 0")
    private Double price;

    public RoomDTO(){}


}
