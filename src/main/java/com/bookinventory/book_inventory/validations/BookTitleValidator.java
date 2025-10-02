package com.bookinventory.book_inventory.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BookTitleValidator implements ConstraintValidator<ValidBookTitle, String>{
    private String titlePattern = "^[a-zA-Z0-9][a-zA-Z0-9 ',?!]+$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;
        return value.trim().matches(titlePattern);
    }
}
