package com.acc.hotelmanagement.controller;

import com.acc.hotelmanagement.dto.BookingDTO;
import com.acc.hotelmanagement.service.BookingHandlerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@AllArgsConstructor
public class BookingController {

    private final BookingHandlerService bookingHandlerService;


    /**
     * Retrieves all bookings with their details.
     *
     * @return ResponseEntity containing a list of BookingDTOs with HTTP status OK.
     */
    @GetMapping
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        List<BookingDTO> bookingDTOs = bookingHandlerService.getAllBookingsWithDetails();
        return new ResponseEntity<>(bookingDTOs, HttpStatus.OK);
    }


    /**
     * Retrieves booking details for the specified booking ID.
     *
     * @param bookingId The ID of the booking to retrieve.
     * @return ResponseEntity containing the BookingDTO for the specified ID with HTTP status OK.
     */
    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDTO> getBooking(@PathVariable long bookingId) {
        BookingDTO bookingDTO = bookingHandlerService.getBookingDetails(bookingId);
        return new ResponseEntity<>(bookingDTO, HttpStatus.OK);
    }


    /**
     * Creates a new booking for the specified room ID.
     * If the bookingDTO indicates that parking is required, assigns a parking space.
     *
     * @param roomId     The ID of the room to book.
     * @param bookingDTO The booking details.
     * @return ResponseEntity containing the BookingDTO for the newly created booking with HTTP status CREATED.
     */
    @PostMapping("/{roomId}")
    public ResponseEntity<BookingDTO> createNewBookingWithRoomId(@PathVariable long roomId, @Valid @RequestBody BookingDTO bookingDTO) {
        return new ResponseEntity<>(bookingHandlerService.createBookingWithOptionalParking(roomId, bookingDTO), HttpStatus.CREATED);
    }

//    @PostMapping
//    public ResponseEntity<BookingDTO> createNewBookings(@Valid @RequestBody BookingDTO bookingDTO) {
//        return new ResponseEntity<>(bookingHandlerService.createBooking(bookingDTO), HttpStatus.CREATED);
//    }


    /**
     * Deletes a booking and releases any associated parking space.
     *
     * @param bookingId The ID of the booking to delete.
     * @return ResponseEntity containing a success message with HTTP status OK.
     */
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<String> deleteBooking(@PathVariable long bookingId) {
        bookingHandlerService.deleteBookingAndReleaseParking(bookingId);
        return new ResponseEntity<>("Booking deleted successfully", HttpStatus.OK);
    }


}
