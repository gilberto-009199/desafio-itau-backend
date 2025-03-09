package com.gilberto009199.itau.desafio_backend.response;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.DoubleSummaryStatistics;

public record DoubleSummaryStatisticsResponse(
    BigInteger count,
    BigDecimal sum,
    BigDecimal avg,
    BigDecimal min,
    BigDecimal max
){

    public DoubleSummaryStatisticsResponse(DoubleSummaryStatistics stats) {
        this(
            BigInteger.valueOf(stats.getCount()),  
            BigDecimal.valueOf(stats.getSum()),    
            BigDecimal.valueOf(stats.getAverage()),
            BigDecimal.valueOf(stats.getMin()),    
            BigDecimal.valueOf(stats.getMax())     
        );
    }

}
