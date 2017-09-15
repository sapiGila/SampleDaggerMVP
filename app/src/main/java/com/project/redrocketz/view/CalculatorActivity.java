package com.project.redrocketz.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.project.redrocketz.R;
import com.project.redrocketz.app.DaggerApp;
import com.project.redrocketz.contract.CalculatorContract;
import com.project.redrocketz.enumeration.Operator;
import com.project.redrocketz.object.Calculator;
import com.project.redrocketz.presenter.CalculatorPresenter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EnumSet;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Dell on 9/14/2017.
 */

public class CalculatorActivity extends AppCompatActivity implements CalculatorContract.View {

    @BindView(R.id.input_value_1)
    EditText inputValue1;
    @BindView(R.id.operation_selector)
    Spinner operationSelector;
    @BindView(R.id.input_value_2)
    EditText inputValue2;
    @BindView(R.id.result_value)
    TextView resultValue;
    @BindView(R.id.result_area)
    ScrollView resultArea;
    @BindView(R.id.history)
    AppCompatTextView history;

    @Inject
    CalculatorPresenter presenter;

    private boolean isVisible = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((DaggerApp) getApplication()).getInjector().getAppComponent().injectCalculatorActivity(this);
        setContentView(R.layout.activity_calculator);
        ButterKnife.bind(this);
        initSpinner();
        presenter.setView(this);
    }

    private void initSpinner() {
        //initialize spinner
        ArrayList<Operator> operators = new ArrayList<>(EnumSet.allOf(Operator.class));
        ArrayAdapter<Operator> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, operators);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        operationSelector.setAdapter(dataAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_history, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_show_history:
                if (isVisible) {
                    resultArea.setVisibility(View.GONE);
                } else {
                    resultArea.setVisibility(View.VISIBLE);
                }
                isVisible = !isVisible;
                return true;
            case R.id.clear_history:
                presenter.clearHistory();
                return true;
            case R.id.action_show_notes:
                startActivity(new Intent(CalculatorActivity.this, NotesActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.observerCalculator();
        presenter.observeResult();
    }

    @OnClick(R.id.calculate_button)
    public void onViewClicked() {
        Calculator request = new Calculator((Operator) operationSelector.getAdapter()
                .getItem(operationSelector.getSelectedItemPosition()),
                inputValue1.getText().toString(),
                inputValue2.getText().toString());
        presenter.handleCalculate(request);
    }

    @Override
    public void updateDisplay(BigDecimal value) {
        resultValue.setText(String.valueOf(value));
    }

    @Override
    public void showHistory(String value) {
        history.setText(value);
    }

    @Override
    public void showNullHistory() {
        history.setText("Tidak Ada History");
    }
}
