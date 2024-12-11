package com.acc.hotelmanagement.dto;

import com.acc.hotelmanagement.model.RoomType;
import jakarta.validation.constraints.NotNull;

public class RoomDTO {

    private Long id;

    @NotNull
    private RoomType type;

    @NotNull
    private int nGuests;

    @NotNull
    private double price;
}
