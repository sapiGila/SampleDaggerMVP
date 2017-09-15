package com.project.redrocketz.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculatorUtil {

    public BigDecimal add(BigDecimal op1, BigDecimal op2) {
        return op1.add(op2);
    }

    public BigDecimal multiply(BigDecimal op1, BigDecimal op2) {
        return op1.multiply(op2);
    }

    public BigDecimal divide(BigDecimal op1, BigDecimal op2) {
        try {
            return op1.divide(op2, 2, RoundingMode.HALF_UP);
        } catch (Exception e) {
            e.printStackTrace();
            return BigDecimal.valueOf(0);
        }
    }

    public BigDecimal subtract(BigDecimal op1, BigDecimal op2) {
        return op1.subtract(op2);
    }
}
