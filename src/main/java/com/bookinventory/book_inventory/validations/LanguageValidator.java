package com.bookinventory.book_inventory.validations;

import java.util.Arrays;
import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LanguageValidator implements ConstraintValidator<ValidLanguage, String>{
    private final List<String> allowedLanguages = Arrays.asList("English", "Tamil", "French", "Spanish");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;
        return allowedLanguages.contains(value);
    }
}
