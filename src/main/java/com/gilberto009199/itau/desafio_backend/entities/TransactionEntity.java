package com.gilberto009199.itau.desafio_backend.entities;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.gilberto009199.itau.desafio_backend.requests.TransactionRequest;

public class TransactionEntity {
    
    private BigDecimal valor;
    private OffsetDateTime dataHora;
    
    public BigDecimal getValor() { return valor;  }
    public TransactionEntity setValor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }

    public OffsetDateTime getDataHora() { return dataHora; }
    public TransactionEntity setDataHora(OffsetDateTime dataHora) {
        this.dataHora = dataHora;
        return this;
    }


    public static TransactionEntity of(TransactionRequest request) {
        return new TransactionEntity()
        .setDataHora(request.dataHora())
        .setValor(request.valor());
    }


    

}
