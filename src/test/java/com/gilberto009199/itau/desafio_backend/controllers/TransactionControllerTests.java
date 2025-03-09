package com.gilberto009199.itau.desafio_backend.controllers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

import org.assertj.core.data.TemporalUnitOffset;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Description;
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
    @Description("Tests Transaction valid value and date")
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
    @Description("Tests Transaction with Time after now")
    void createTransactionInValidDate(){

        var request = new TransactionRequest(
            BigDecimal.valueOf(100),
            // now + 100 min
            OffsetDateTime.now().plus(100, ChronoUnit.MINUTES)
        );
		

        var response = restAPI.postForEntity( path, request, null);


        Assertions.assertEquals( HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());

    }
}
