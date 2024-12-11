package com.acc.hotelmanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "rooms")
@Getter
@Setter
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private RoomType type;
    private int nGuests;
    private double price;

    @OneToMany(mappedBy = "room")
    private List<Booking> bookings;


    public Room() {
    }

    public Room(RoomType type, int nGuests, double price) {
        this.type = type;
        this.nGuests = nGuests;
        this.price = price;
    }
}
