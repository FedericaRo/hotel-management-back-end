package com.acc.hotelmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class BookingDTO {

    private Long id;

    @NotBlank
    @DateTimeFormat(pattern="dd-MMM-yyyy")
    private LocalDate checkInDate;

    @NotBlank
    @DateTimeFormat(pattern="dd-MMM-yyyy")
    private LocalDate checkOutDate;


}
