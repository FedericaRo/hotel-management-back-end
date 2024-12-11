package com.acc.hotelmanagement.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;

//  private int nGuests;
    @ManyToOne
    @JoinColumn(name="room_id", nullable = false)
    private Room room;

    @OneToOne
    @JoinColumn(name = "parking_space_id")
    private ParkingSpace parkingSpace;
}
