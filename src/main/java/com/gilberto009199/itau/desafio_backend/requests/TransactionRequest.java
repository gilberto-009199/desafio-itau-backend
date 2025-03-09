package com.gilberto009199.itau.desafio_backend.requests;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.gilberto009199.itau.desafio_backend.validation.BeforeNowTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record TransactionRequest( 
    @NotNull 
    @Min(value = 0, message="valor >= 0")
    BigDecimal valor,
    
    @NotNull
    @BeforeNowTime(message = "dataHora < Data Atual")
    OffsetDateTime dataHora
){}
