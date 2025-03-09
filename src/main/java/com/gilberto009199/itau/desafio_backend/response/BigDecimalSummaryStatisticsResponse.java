package com.gilberto009199.itau.desafio_backend.response;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.DoubleSummaryStatistics;

public class BigDecimalSummaryStatisticsResponse{

    private  BigInteger count;
    private  BigDecimal sum;
    private  BigDecimal avg;
    private  BigDecimal min;
    private  BigDecimal max;

    public BigDecimalSummaryStatisticsResponse() {
        
        this.count = BigInteger.valueOf(0);
        this.sum = BigDecimal.valueOf(0);
        this.avg = BigDecimal.valueOf(0);
        this.min = BigDecimal.valueOf(0);
        this.max = BigDecimal.valueOf(0);    
        
    }
    

    public void accept(BigDecimal value) {
        
        count.add(BigInteger.ONE);

        sum = sum.add(value);
        min = min.min(value);
        max = max.max(value);
        
        avg = sum.divide(new BigDecimal(count), 2, RoundingMode.HALF_UP);
    }

}
