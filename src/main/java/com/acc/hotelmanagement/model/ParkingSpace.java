package com.acc.hotelmanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "parking_spaces")
public class ParkingSpace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ParkingCode code;

    private boolean assigned;

    @OneToOne(mappedBy = "parkingSpace")
    private Booking booking;

    @PrePersist
    public void onCreate()
    {
        assigned   = false;
    }

    public ParkingSpace(){}
    
    public ParkingSpace(ParkingCode code){
        this.code = code;
    }
}
