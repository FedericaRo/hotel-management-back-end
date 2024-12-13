package com.acc.hotelmanagement.service;

import com.acc.hotelmanagement.model.ParkingSpace;

public interface ParkingSpaceService {

    public ParkingSpace reserveParkingSpace();

    public void removeParkingSpace(Long parkingSpaceId);

}
