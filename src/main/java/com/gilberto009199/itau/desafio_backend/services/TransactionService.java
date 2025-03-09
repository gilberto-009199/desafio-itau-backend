package com.gilberto009199.itau.desafio_backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gilberto009199.itau.desafio_backend.repositories.TransactionRepository;
import com.gilberto009199.itau.desafio_backend.requests.TransactionRequest;

@Service
public class TransactionService {

    private TransactionRepository repository;

    public TransactionService(TransactionRepository repository){
        this.repository = repository;
    }


    public TransactionRequest save(TransactionRequest transaction) {
        return repository.save(transaction);
    }


    public void deleteAll() {
        repository.deleteAll();
    }

}
