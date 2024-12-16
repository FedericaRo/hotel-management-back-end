package com.acc.hotelmanagement.validation;

import com.acc.hotelmanagement.model.RoomType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class RoomTypeValidator implements ConstraintValidator<ValidRoomType, String> {

    /**
     * Validate that the provided string is a valid room type.
     * The validation is case-insensitive.
     * If the provided string is null, the validation is successful, the null case is handles by the @NotNull annotation
     *
     * @param value the string to validate
     * @param context the constraint validator context
     * @return true if the string is a valid room type, false otherwise
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        // Check if the provided string is equal to any of the room types (case-insensitive)
        return Arrays.stream(RoomType.values())
                .anyMatch(enumValue -> enumValue.name().equalsIgnoreCase(value));
    }
}
