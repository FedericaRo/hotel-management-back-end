package com.acc.hotelmanagement.validation;

import com.acc.hotelmanagement.model.RoomType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class RoomTypeValidator implements ConstraintValidator<ValidRoomType, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return Arrays.stream(RoomType.values())
                .anyMatch(enumValue -> enumValue.name().equalsIgnoreCase(value));
    }
}
