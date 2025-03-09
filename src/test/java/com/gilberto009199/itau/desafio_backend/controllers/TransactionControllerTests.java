package com.gilberto009199.itau.desafio_backend.controllers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

import com.gilberto009199.itau.desafio_backend.requests.TransactionRequest;

@SpringBootTest
public class TransactionControllerTests {
 
    /* Componente para interagir com a API */
	@Autowired
	protected TestRestTemplate restAPI;

    @Test
    void createTransactionValid(){
        var request = new TransactionRequest(
            BigDecimal.valueOf(100),
            OffsetDateTime.now()
        );
		String path = "/transaction";

        var response = restAPI.postForEntity( path, request, null);


        Assertions.assertEquals( HttpStatus.CREATED, response.getStatusCode());


    }

}
