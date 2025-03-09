package com.gilberto009199.itau.desafio_backend.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@Target( { FIELD, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = BeforeNowTimeValid.class)
public @interface BeforeNowTime {
    public String message() default "Celular Incorreto";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}