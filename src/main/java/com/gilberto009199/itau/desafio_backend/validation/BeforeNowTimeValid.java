package com.gilberto009199.itau.desafio_backend.validation;

import java.time.OffsetDateTime;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BeforeNowTimeValid implements ConstraintValidator<BeforeNowTime, OffsetDateTime> {
    // Valida Transaction datetime
    public boolean isValid(OffsetDateTime date, ConstraintValidatorContext cxt) {
        return date.isBefore(OffsetDateTime.now());
    }
}