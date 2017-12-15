package com.example.depp1715.prog3210;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class TipCalcActivity extends AppCompatActivity implements View.OnKeyListener, View.OnClickListener {

    private EditText billAmountEditText;
    private TextView percentTextView;
    private Button percentUpButton;
    private Button   percentDownButton;
    private TextView tipTextView;
    private TextView totalTextView;
    private CheckBox roundUp;

    // tip percent variable - default to 20%
    private float tipPercent = .20f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calc);

        billAmountEditText = findViewById(R.id.billAmountEditText);
        percentTextView = findViewById(R.id.percentTextView);
        percentUpButton = findViewById(R.id.percentUpButton);
        percentDownButton = findViewById(R.id.percentDownButton);
        tipTextView = findViewById(R.id.tipTextView);
        totalTextView = findViewById(R.id.totalTextView);
        roundUp = findViewById(R.id.checkbox_roundup);

        // set event listeners for all inputs
        billAmountEditText.setOnKeyListener(this);
        percentUpButton.setOnClickListener(this);
        percentDownButton.setOnClickListener(this);
        roundUp.setOnClickListener(this);

        // calculate the initial tip amount
        calculateAndDisplay();
    }

    public void calculateAndDisplay() {
        // get the bill amount
        String billAmountString = billAmountEditText.getText().toString();
        float billAmount;
        if (billAmountString.equals("")) {
            billAmount = 0;
        }
        else {
            billAmount = Float.parseFloat(billAmountString);
        }

        // calculate tip and total
        float tipAmount = billAmount * tipPercent;
        float totalAmount = billAmount + tipAmount;

        if (roundUp.isChecked()){
            // Round up to the nearest dollar
            // TODO: Round up to the nearest $5 or $10
            totalAmount = (float)Math.ceil(totalAmount);
            tipAmount = totalAmount - billAmount;
        }

        // display the results with formatting
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        tipTextView.setText(currency.format(tipAmount));
        totalTextView.setText(currency.format(totalAmount));

        NumberFormat percent = NumberFormat.getPercentInstance();
        percentTextView.setText(percent.format(tipPercent));
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        //TODO: Allow only 2 decimal places
        calculateAndDisplay();
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.percentDownButton:
                tipPercent = tipPercent - .01f;
                calculateAndDisplay();
                break;
            case R.id.percentUpButton:
                tipPercent = tipPercent + .01f;
                calculateAndDisplay();
                break;
            default:
                calculateAndDisplay();
                break;
        }
    }
}
