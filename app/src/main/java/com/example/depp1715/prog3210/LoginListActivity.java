package com.example.depp1715.prog3210;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class LoginListActivity extends AppCompatActivity {


    private UserSessionManager session;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_list);
        session = new UserSessionManager(getApplicationContext());
        database = AppDatabase.getDatabase(getApplicationContext());

        List<LoginLog> logins = database.loginLogDao().getLogins();
        String[] loginsArray = new String[logins.size()];
        for (int i = 0; i < logins.size(); i++) {
            loginsArray[i] = formatDateString(logins.get(i).dateLoggedIn) + ": " + database.userDao().getUser(logins.get(i).userId).username;
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, loginsArray);
        ListView listView = (ListView) findViewById(R.id.lblLoginsList);
        listView.setAdapter(adapter);
    }
    public String formatDateString(String dateString){
        String tempDateString = dateString;
        dateString = dateString.substring(0, 4) + "/";
        dateString += tempDateString.substring(4, 6) + "/";
        dateString += tempDateString.substring(6, 8) + " ";
        dateString += tempDateString.substring(9, 11) + ":";
        dateString += tempDateString.substring(11, 13) + ":";
        dateString += tempDateString.substring(13, 15);
        return dateString;
    }
}
