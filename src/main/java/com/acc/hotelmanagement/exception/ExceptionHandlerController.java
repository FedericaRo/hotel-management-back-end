package com.acc.hotelmanagement.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public String entityNotFoundException(EntityNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(InvalidBookingDateException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public String invalidBookingDateException(InvalidBookingDateException e) {
        return e.getMessage();
    }

    @ExceptionHandler(RoomNotAvailableException.class)
    @ResponseStatus(code = HttpStatus.CONFLICT)
    public String roomNotAvailableException(RoomNotAvailableException e) {
        return e.getMessage();
    }


}
