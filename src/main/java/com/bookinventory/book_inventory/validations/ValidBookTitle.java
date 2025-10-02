package com.bookinventory.book_inventory.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = BookTitleValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidBookTitle {
    String message() default "Invalid Title. Only letters, numbers, spaces, commas, punctuations and period allowed.";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
