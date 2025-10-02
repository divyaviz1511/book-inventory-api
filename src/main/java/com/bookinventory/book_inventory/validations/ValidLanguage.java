package com.bookinventory.book_inventory.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = LanguageValidator.class)
@Target(ElementType.FIELD) //Tells where we are use this annotation on
@Retention(RetentionPolicy.RUNTIME) //does validation during runtime
public @interface ValidLanguage { //This creates custom annotation

    String message() default "Language not supported. Must be English, French, Tamil or Spanish";
    //Need to add these two lines as they are required
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    
}
