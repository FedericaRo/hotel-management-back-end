package com.acc.hotelmanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoomType type;
    private int numberOfGuests;
    private double price;

    @ToString.Exclude
    @OneToMany(mappedBy = "room")
    private List<Booking> bookings;


    public Room() {
    }

    public Room(RoomType type, int nGuests, double price) {
        this.type = type;
        this.numberOfGuests = nGuests;
        this.price = price;
    }


}
