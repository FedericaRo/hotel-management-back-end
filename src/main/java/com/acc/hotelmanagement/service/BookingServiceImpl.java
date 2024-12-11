package com.acc.hotelmanagement.service;

import com.acc.hotelmanagement.model.Booking;
import com.acc.hotelmanagement.repository.BookingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<Booking> getAllBookings() {
        System.out.println("here");
        System.out.println(bookingRepository.findAll());
        return bookingRepository.findAll();
    }

    @Override
    public Booking createNewBooking() {
        return null;
    }
}
