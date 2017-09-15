package com.project.redrocketz.presenter;

import android.text.TextUtils;

import com.project.redrocketz.contract.CalculatorContract;
import com.project.redrocketz.model.CalculatorModel;
import com.project.redrocketz.model.CalculatorRepository;
import com.project.redrocketz.object.Calculator;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Dell on 7/4/2017.
 */

public class CalculatorPresenter implements CalculatorContract.Presenter {

    private CalculatorContract.View view;
    private CalculatorModel model;
    private PublishSubject<Calculator> calculatorSubject = PublishSubject.create();
    private PublishSubject<String> resultSubject = PublishSubject.create();

    @Inject
    public CalculatorPresenter(CalculatorRepository model) {
        this.model = model;
    }

    public void setView(CalculatorContract.View view) {
        this.view = view;
    }

    @Override
    public void handleCalculate(Calculator calculator) {
        calculatorSubject.onNext(calculator);
    }

    @Override
    public void observerCalculator() {
        Observer<Calculator> observer = new Observer<Calculator>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
            }

            @Override
            public void onNext(@NonNull Calculator calculator) {
                if (calculator != null) {
                    doCalculate(calculator);
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        };
        calculatorSubject.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void observeResult() {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
            }

            @Override
            public void onNext(@NonNull String s) {
                if (StringUtils.isNotBlank(s)) {
                    view.showHistory(s);
                } else {
                    view.showNullHistory();
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        };
        resultSubject.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void clearHistory() {
        model.clearHistory();
        resultSubject.onNext(model.getHistory());
    }

    @Override
    public void getHistory() {
        resultSubject.onNext(model.getHistory());
    }

    private void doCalculate(Calculator calculator) {
        BigDecimal result = BigDecimal.valueOf(0);
        switch (calculator.getOperator()) {
            case ADD:
                result = model.add(convertBigDecimalValue(calculator.getInputValue1()),
                        convertBigDecimalValue(calculator.getInputValue2()));
                break;
            case MUL:
                result = model.multiply(convertBigDecimalValue(calculator.getInputValue1()),
                        convertBigDecimalValue(calculator.getInputValue2()));
                break;
            case DIV:
                result = model.divide(convertBigDecimalValue(calculator.getInputValue1()),
                        convertBigDecimalValue(calculator.getInputValue2()));
                break;
            case SUB:
                result = model.subtract(convertBigDecimalValue(calculator.getInputValue1()),
                        convertBigDecimalValue(calculator.getInputValue2()));
                break;
        }
        model.storeHistory(result);
        view.updateDisplay(result);
        resultSubject.onNext(model.getHistory());
    }

    private BigDecimal convertBigDecimalValue(String text) {
        String value = "0";
        if (!TextUtils.isEmpty(text)) {
            value = text;
        }
        return BigDecimal.valueOf(Double.valueOf(value));
    }
}