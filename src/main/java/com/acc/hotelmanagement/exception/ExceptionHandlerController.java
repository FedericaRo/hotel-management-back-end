package com.acc.hotelmanagement.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public String illegalArgumentException(IllegalArgumentException e) {
        return e.getMessage();
    }

    @ExceptionHandler(ParkingSpaceException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public String noAvailableParkingSpaceException(ParkingSpaceException e) {
        return e.getMessage();
    }
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
//    public String MethodArgumentNotValidException(MethodArgumentNotValidException e) {
//        return e.getBindingResult().getFieldError().getDefaultMessage();
//
//    }

    /**
     * Handles validation errors from input DTOs with annotation validation. (ex. @NotNull, @Min, etc.)
     * Returns the specific field error message if available, otherwise returns a fallback message.
     *
     * @param e the exception thrown when method argument validation fails
     * @return the error message
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public String methodArgumentNotValidException(MethodArgumentNotValidException e) {
        // Retrieve the first field error from the binding result
        FieldError fieldError = e.getBindingResult().getFieldError();

        if (fieldError != null && fieldError.getDefaultMessage() != null)
            return fieldError.getDefaultMessage(); // Return the specific field error message

        // Return the exception's message as a fallback
        return e.getMessage();
    }



}
