package com.project.redrocketz.model;

import com.project.redrocketz.utils.CalculatorUtil;
import com.project.redrocketz.utils.PreferenceManager;
import com.project.redrocketz.utils.StringHelper;

import java.math.BigDecimal;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Dell on 7/4/2017.
 */

@Singleton
public class CalculatorRepository implements CalculatorModel {

    private CalculatorUtil calculatorUtil;
    private PreferenceManager preferenceManager;

    @Inject
    public CalculatorRepository(CalculatorUtil calculatorUtil,
                                PreferenceManager preferenceManager) {
        this.calculatorUtil = calculatorUtil;
        this.preferenceManager = preferenceManager;
    }

    @Override
    public BigDecimal add(BigDecimal op1, BigDecimal op2) {
        return calculatorUtil.add(op1, op2);
    }

    @Override
    public BigDecimal multiply(BigDecimal op1, BigDecimal op2) {
        return calculatorUtil.multiply(op1, op2);
    }

    @Override
    public BigDecimal divide(BigDecimal op1, BigDecimal op2) {
        return calculatorUtil.divide(op1, op2);
    }

    @Override
    public BigDecimal subtract(BigDecimal op1, BigDecimal op2) {
        return calculatorUtil.subtract(op1, op2);
    }

    @Override
    public void storeHistory(BigDecimal value) {
        String result = StringHelper.getStringBuilderToString(preferenceManager.getHistory(),
                "\n",
                String.valueOf(value));
        preferenceManager.setHistory(result);
    }

    @Override
    public String getHistory() {
        return preferenceManager.getHistory();
    }

    @Override
    public void clearHistory() {
        preferenceManager.setHistory("");
    }
}
