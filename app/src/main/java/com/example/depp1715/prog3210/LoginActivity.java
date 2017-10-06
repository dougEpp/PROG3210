package com.example.depp1715.prog3210;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText txtUserName;
    private EditText txtPassword;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUserName = (EditText) findViewById(R.id.txtUserName);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent mainActivityIntent = new Intent(this, MainActivity.class);

        String userName = txtUserName.getText().toString();
        String password = txtPassword.getText().toString();

        if (userName.equals(getString(R.string.stored_username)) && password.equals(getString(R.string.stored_password))){
            //Navigate to Main activity and stop the login activity
            startActivity(mainActivityIntent);
            this.finish();
        } else {
            CharSequence msgLoginIncorrect = getText(R.string.login_incorrect);
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(LoginActivity.this, msgLoginIncorrect, duration);
            toast.show();
        }
    }
}
