package com.gilberto009199.itau.desafio_backend.services;

import java.time.OffsetDateTime;


import org.springframework.stereotype.Service;

import com.gilberto009199.itau.desafio_backend.entities.TransactionEntity;
import com.gilberto009199.itau.desafio_backend.repositories.TransactionRepository;
import com.gilberto009199.itau.desafio_backend.requests.TransactionRequest;
import com.gilberto009199.itau.desafio_backend.response.BigDecimalSummaryStatisticsResponse;

@Service
public class TransactionService {

    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository){
        this.repository = repository;
    }


    public TransactionEntity save(TransactionRequest transaction) {
        return repository.save(transaction);
    }


    public void deleteAll() {
        repository.deleteAll();
    }


    public BigDecimalSummaryStatisticsResponse calcStatistics() {
        var list = repository.findAll();
        var now = OffsetDateTime.now().minusSeconds(60);
        var stats = new BigDecimalSummaryStatisticsResponse();

        list
        .stream()
        .filter(t -> t.getDataHora().isAfter(now))
        .forEach(t -> stats.accept(t.getValor())); 

        return stats;
    }

}
