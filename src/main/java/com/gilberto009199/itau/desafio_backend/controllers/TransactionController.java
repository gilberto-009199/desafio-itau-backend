package com.gilberto009199.itau.desafio_backend.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gilberto009199.itau.desafio_backend.requests.TransactionRequest;
import com.gilberto009199.itau.desafio_backend.services.TransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transacao")
public class TransactionController {
    
    public static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    private final TransactionService service;

    public TransactionController(TransactionService service){
        this.service = service;
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<?> saveTransaction(   @RequestBody 
                                                @Valid
                                                TransactionRequest request){

        logger.debug("stage=init method=saveTransaction: {}", request);
        
        service.save(request);

        logger.debug("stage=end method=saveTransaction: {}", request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    @ResponseStatus(code = HttpStatus.OK)
    @DeleteMapping
    public ResponseEntity<?> clearTransactions(){
        logger.debug("stage=init method=clearTransactions");
        
        service.deleteAll();

        logger.debug("stage=end method=clearTransactions");
        return ResponseEntity.ok(null);
    }
}
