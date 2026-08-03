package com.jazz.junit.nurzat.bankapp.functional;

import java.math.BigDecimal;

@FunctionalInterface
public interface FeeCalculator {

    BigDecimal calculate(BigDecimal amount);

    default FeeCalculator plus(FeeCalculator other){
        return amount -> this.calculate(amount).add(other.calculate(amount));
    }
    static FeeCalculator flat(BigDecimal fixedFee){
        return amount -> fixedFee;
    }
    static FeeCalculator percentOfAmount(BigDecimal percent){
        return amount -> amount.multiply(percent);
    }
}
