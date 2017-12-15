package com.example.depp1715.prog3210;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText txtUserName;
    private EditText txtPassword;
    private Button btnSubmit;
    private User user;
    private AppDatabase database;
    UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        session = new UserSessionManager(getApplicationContext());

        if(session.isUserLoggedIn()){
            Intent mainActivityIntent = new Intent(this, MainActivity.class);

            //Navigate to Main activity and stop the login activity
            startActivity(mainActivityIntent);
            this.finish();
            return;
        }
        database = AppDatabase.getDatabase(getApplicationContext());

        // cleanup for testing some initial data
        //database.userDao().removeAllUsers();
        // add some data
        List<User> users = database.userDao().getAllUser();
        if (users.size()==0) {
            database.userDao().addUser(new User("doug", "password"));
            database.userDao().addUser(new User("notDoug", "initial"));
            database.userDao().addUser(new User("Test 3", "initial"));
        }

        txtUserName = (EditText) findViewById(R.id.txtUserName);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent mainActivityIntent = new Intent(this, MainActivity.class);

        String username = txtUserName.getText().toString();
        String password = txtPassword.getText().toString();

        int memberId = database.userDao().loginUser(username, password);
        if (memberId != 0){
            user = database.userDao().getUser(memberId);
            session.createUserLoginSession(user);

            DateFormat df = new SimpleDateFormat("yyyyMMdd HHmmss");
            Date now = Calendar.getInstance().getTime();
            String dateString = df.format(now);
            if (dateString != null){
                LoginLog login = new LoginLog(dateString, user.id);
                database.loginLogDao().addLogin(login);
            }
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
