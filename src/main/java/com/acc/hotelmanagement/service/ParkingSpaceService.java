package com.acc.hotelmanagement.service;

import com.acc.hotelmanagement.model.ParkingSpace;

public interface ParkingSpaceService {

    ParkingSpace reserveParkingSpace();

    void removeParkingSpace(Long parkingSpaceId);

}
