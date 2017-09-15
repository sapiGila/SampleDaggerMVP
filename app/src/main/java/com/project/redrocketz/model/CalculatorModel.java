package com.project.redrocketz.model;

import java.math.BigDecimal;

/**
 * Created by Dell on 7/4/2017.
 */

public interface CalculatorModel {

    BigDecimal add(BigDecimal op1, BigDecimal op2);
    BigDecimal multiply(BigDecimal op1, BigDecimal op2);
    BigDecimal divide(BigDecimal op1, BigDecimal op2);
    BigDecimal subtract(BigDecimal op1, BigDecimal op2);
    void storeHistory(BigDecimal value);
    String getHistory();
    void clearHistory();
}
