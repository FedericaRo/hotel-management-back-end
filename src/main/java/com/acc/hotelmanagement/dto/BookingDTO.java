package com.acc.hotelmanagement.dto;

import com.acc.hotelmanagement.model.ParkingSpace;
import com.acc.hotelmanagement.model.Room;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

public class BookingDTO {

    private Long id;

    @NotNull
    private String type;

    @NotNull
    private int nGuests;

    @NotNull
    private double price;
}
