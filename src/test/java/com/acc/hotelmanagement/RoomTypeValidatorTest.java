package com.acc.hotelmanagement;

import com.acc.hotelmanagement.validation.RoomTypeValidator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RoomTypeValidatorTest {

    private final RoomTypeValidator validator = new RoomTypeValidator();

    @Test
    void testValidRoomType() {
        // Tests if the validator correctly identifies valid room types
        assertTrue(validator.isValid("SUITE", null));
        assertTrue(validator.isValid("CLASSIC", null));
        assertTrue(validator.isValid("MODERN", null));
        assertTrue(validator.isValid("BASIC", null));
    }

    @Test
    void testInvalidRoomType() {
        // Tests if validator returns false with an invalid room type
        assertFalse(validator.isValid("INVALID_TYPE", null));
    }

    @Test
    void testNullRoomType() {
        assertFalse(validator.isValid(null, null));
    }

//    @Test
//    void testValidRoomNGuest() {
//        // Tests if the validator correctly identifies valid room types
//        RoomDTO roomDTO = new RoomDTO;
//        roomDTO.setType(RoomType.CLASSIC);
//        roomDTO.setNumberOfGuests(1);
//        roomDTO.setPrice(45);
//    }
}
