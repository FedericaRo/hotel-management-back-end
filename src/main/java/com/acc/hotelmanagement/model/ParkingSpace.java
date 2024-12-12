package com.acc.hotelmanagement.model;

import jakarta.persistence.*;

@Entity
@Table(name = "parking_spaces")
public class ParkingSpace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ParkingCode code;

    private boolean reserved;

    @OneToOne(mappedBy = "parkingSpace")
    Booking booking;
}
