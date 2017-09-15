package com.project.redrocketz.contract;

import com.project.redrocketz.object.Calculator;

import java.math.BigDecimal;

/**
 * Created by Dell on 9/14/2017.
 */

public interface CalculatorContract {
    interface Presenter{
        void handleCalculate(Calculator calculator);
        void observerCalculator();
        void observeResult();
        void clearHistory();
        void getHistory();
    }
    interface View {
        void updateDisplay(BigDecimal value);
        void showHistory(String value);
        void showNullHistory();
    }
}
