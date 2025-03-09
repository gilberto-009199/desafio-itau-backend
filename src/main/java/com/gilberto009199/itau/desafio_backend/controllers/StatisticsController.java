package com.gilberto009199.itau.desafio_backend.controllers;

import java.net.http.HttpResponse.ResponseInfo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatistica")
public class StatisticsController {
    @GetMapping
    public ResponseEntity<?> getStatistics(){
        return ResponseEntity.ok(null);
    }
}
