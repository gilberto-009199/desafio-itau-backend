package com.gilberto009199.itau.desafio_backend.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gilberto009199.itau.desafio_backend.response.BigDecimalSummaryStatisticsResponse;
import com.gilberto009199.itau.desafio_backend.services.TransactionService;

@RestController
@RequestMapping("/estatistica")
public class StatisticsController {

    public static final Logger logger = LoggerFactory.getLogger(StatisticsController.class);
    
    private final TransactionService service;

    public StatisticsController(TransactionService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<BigDecimalSummaryStatisticsResponse> getStatistics(){
        return ResponseEntity.ok(service.calcStatistics());
    }
}
