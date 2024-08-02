package com.bronya.projdemo.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {StateValidation.class})
public @interface State {
    String message() default "state: 0 as BETA, 1 as RELEASE";

    Class<?>[] groups() default {}; // !important

    Class<? extends Payload>[] payload() default {}; // !important
}

class StateValidation implements ConstraintValidator<State, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value == 0 || value == 1;
    }
}
