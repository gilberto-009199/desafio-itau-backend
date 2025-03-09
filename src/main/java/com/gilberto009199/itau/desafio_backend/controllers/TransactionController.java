package com.gilberto009199.itau.desafio_backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacao")
public class TransactionController {

    @PostMapping
    public ResponseEntity<?> saveTransaction(){
        return ResponseEntity.ok(null);
    }

    @DeleteMapping
    public ResponseEntity<?> clearTransactions(){
        return ResponseEntity.ok(null);
    }
}
