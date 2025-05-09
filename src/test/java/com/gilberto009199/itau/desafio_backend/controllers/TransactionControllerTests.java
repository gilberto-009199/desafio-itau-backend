package com.gilberto009199.itau.desafio_backend.controllers;

import java.math.BigDecimal;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gilberto009199.itau.desafio_backend.requests.TransactionRequest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class TransactionControllerTests {
 
    /* Componente para interagir com a API */
	@Autowired
	protected TestRestTemplate restAPI;
    protected String path = "/transacao";

    @Test
    @DisplayName("Tests Transaction valid value and date")
    void createTransactionValid(){
        
        var request = new TransactionRequest(
            BigDecimal.valueOf(100),
            // now - 100 min
            OffsetDateTime.now().minus(100, ChronoUnit.MINUTES)
        );
		

        var response = restAPI.postForEntity( path, request, null);


        Assertions.assertEquals( HttpStatus.CREATED, response.getStatusCode());

    }


    @Test
    @DisplayName("Tests Transaction with value negative")
    void createTransactionInValidValue(){

        var request = new TransactionRequest(
            BigDecimal.valueOf( -1),
            // now + 100 min
            OffsetDateTime.now()
        );
		

        var response = restAPI.postForEntity( path, request, null);


        Assertions.assertEquals( HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
    }

    @Test
    @DisplayName("Tests Transaction with Time after now")
    void createTransactionInValidDate(){

        var request = new TransactionRequest(
            BigDecimal.valueOf(100),
            // now + 100 min
            OffsetDateTime.now().plus(100, ChronoUnit.MINUTES)
        );
		

        var response = restAPI.postForEntity( path, request, null);


        Assertions.assertEquals( HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
    }


    @Test
    @DisplayName("Tests Delete All Transactions")
    void deleteTransations(){

        var response= restAPI.exchange(path, 
            HttpMethod.DELETE,
            null,
            Void.class);

        Assertions.assertEquals( HttpStatus.OK, response.getStatusCode());
    }
}
