package com.gilberto009199.itau.desafio_backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gilberto009199.itau.desafio_backend.requests.TransactionRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transacao")
public class TransactionController {

    @PostMapping
    public ResponseEntity<?> saveTransaction(   @RequestBody 
                                                @Valid
                                                TransactionRequest request){

        

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<?> clearTransactions(){
        return ResponseEntity.ok(null);
    }
}
