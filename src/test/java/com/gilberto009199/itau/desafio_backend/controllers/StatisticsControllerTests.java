package com.gilberto009199.itau.desafio_backend.controllers;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.OffsetDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gilberto009199.itau.desafio_backend.requests.TransactionRequest;
import com.gilberto009199.itau.desafio_backend.response.BigDecimalSummaryStatisticsResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class StatisticsControllerTests {

    /* Componente para interagir com a API */
	@Autowired
	protected TestRestTemplate restAPI;
    protected String path = "/estatistica";
    protected String pathTransaction = "/transacao";

    @Test
    @DisplayName("Tests Consult Statisctic ")
    public void statistic(){

        var response = restAPI.getForEntity(path, BigDecimalSummaryStatisticsResponse.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    @DisplayName("Tests Calc Statisctic for One Transaction")
    public void statisticCalcOneTransaction(){

        var request = new TransactionRequest(
            BigDecimal.valueOf(100),
            // now
            OffsetDateTime.now()
        );
		
        restAPI.postForEntity( pathTransaction, request, null);

        var response    = restAPI.getForEntity(path, BigDecimalSummaryStatisticsResponse.class);
        var body        = response.getBody();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        
        Assertions.assertEquals(BigInteger.valueOf(1)  , body.getCount());
        Assertions.assertEquals(BigDecimal.valueOf(100), body.getAvg());
        Assertions.assertEquals(BigDecimal.valueOf(100), body.getMin());
        Assertions.assertEquals(BigDecimal.valueOf(100), body.getMax());

    }


    @Test
    @DisplayName("Tests Calc Statisctic for Two Transaction")
    public void statisticCalcTwoTransaction(){

        // clear transaction
        restAPI.delete( pathTransaction );

        var one = new TransactionRequest(
            BigDecimal.valueOf(100),
            // now
            OffsetDateTime.now()
        );
		
        restAPI.postForEntity( pathTransaction, one, null);

        var two = new TransactionRequest(
            BigDecimal.valueOf(1000),
            // now
            OffsetDateTime.now()
        );

        restAPI.postForEntity( pathTransaction, two, null);

        var response    = restAPI.getForEntity(path, BigDecimalSummaryStatisticsResponse.class);
        var body        = response.getBody();


        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        
        Assertions.assertEquals(BigInteger.valueOf(2)  , body.getCount());
        
        var avg = one.valor().add( two.valor() ).divide(BigDecimal.valueOf(2));
        Assertions.assertEquals(avg, body.getAvg());
        
        var min = one.valor().min(two.valor());
        Assertions.assertEquals(min, body.getMin());

        var max = one.valor().max( two.valor() );
        Assertions.assertEquals(max, body.getMax());

    }

}
