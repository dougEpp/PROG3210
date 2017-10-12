package com.example.depp1715.prog3210;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Arrays;

public class UtilActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtTextToManipulate;
    private Button btnManipulateText;
    private RadioButton rReverse;
    private RadioButton rAlphabetize;
    private CheckBox chkCapitalize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_util);


        txtTextToManipulate = (EditText) findViewById(R.id.txtTextToManipulate);
        btnManipulateText = (Button) findViewById(R.id.btnReverseText);
        rReverse = (RadioButton) findViewById(R.id.radioReverse);
        rAlphabetize = (RadioButton) findViewById(R.id.radioAlphabetize);
        chkCapitalize = (CheckBox) findViewById(R.id.chkCapitalize);

        btnManipulateText.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String textToManipulate = txtTextToManipulate.getText().toString();
        String message = "";

        if (rReverse.isChecked()){
            message = new StringBuilder(textToManipulate).reverse().toString();
        } else {
            char[] textChars = textToManipulate.toCharArray();
            Arrays.sort(textChars);
            message = new String(textChars);
        }

        if (chkCapitalize.isChecked()){
            message = message.toUpperCase();
        }

        Toast toast = Toast.makeText(UtilActivity.this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
