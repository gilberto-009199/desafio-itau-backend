package com.gilberto009199.itau.desafio_backend.services;

import java.time.OffsetDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.gilberto009199.itau.desafio_backend.entities.TransactionEntity;
import com.gilberto009199.itau.desafio_backend.repositories.TransactionRepository;
import com.gilberto009199.itau.desafio_backend.requests.TransactionRequest;
import com.gilberto009199.itau.desafio_backend.response.BigDecimalSummaryStatisticsResponse;

@Service
public class TransactionService {

    public static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository){
        this.repository = repository;
    }


    public TransactionEntity save(TransactionRequest transaction) {
        logger.debug("stage=init method=save: {}", transaction);
        
        var entity = repository.save(transaction);

        logger.debug("stage=end method=save: {}", entity);

        return entity;
    }


    public void deleteAll() {
        logger.debug("stage=init method=deleteAll");
        
        repository.deleteAll();

        logger.debug("stage=init method=deleteAll");
    }


    public BigDecimalSummaryStatisticsResponse calcStatistics() {
        
        logger.debug("stage=init method=calcStatistics");

        var list = repository.findAll();
        var now = OffsetDateTime.now().minusSeconds(60);
        var stats = new BigDecimalSummaryStatisticsResponse();

        logger.debug("stage=debug method=calcStatistics: list:{}", list);

        list
        .stream()
        .filter(t -> t.getDataHora().isAfter(now))
        .forEach(t -> stats.accept(t.getValor())); 
        
        logger.debug("stage=end method=calcStatistics: stats:{}", stats);

        return stats;
    }

}
