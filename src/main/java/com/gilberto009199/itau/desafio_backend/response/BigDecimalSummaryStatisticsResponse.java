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
        
        this.count  = BigInteger.valueOf(0);
        
    }
    

    public void accept(BigDecimal value) {
        
        count   = count.add(BigInteger.ONE);
        
        sum     = (sum != null) ? sum.add(value): value;
        min     = (min != null) ? min.min(value): value;
        max     = (max != null) ? max.max(value): value;
        
        avg     = sum.divide(new BigDecimal(count));
    }


    public BigInteger getCount() { return count;  }

    public BigDecimal getSum() { return sum;  }

    public BigDecimal getAvg() { return avg;  }

    public BigDecimal getMin() { return min;  }

    public BigDecimal getMax() { return max;  }
    
}
