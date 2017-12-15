package com.example.depp1715.prog3210;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    UserSessionManager session;
    User user;
    Button btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        session = new UserSessionManager(getApplicationContext());
        user = AppDatabase.getDatabase(getApplicationContext()).userDao().getUser(Integer.parseInt(session.getUserDetails().get(UserSessionManager.KEY_ID)));
        getSupportActionBar().setTitle("Welcome, " + user.username);
        int numColumns;

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            numColumns = 3;
        } else {
            numColumns = 5;
        }

        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(this);

        GridView gridview = (GridView) findViewById(R.id.toolboxGridview);
        gridview.setAdapter(new ImageAdapter(this));
        gridview.setNumColumns(numColumns);

        gridview.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent activityIntent;
        switch (i){
            case 0:
                activityIntent = new Intent(this, TextManipActivity.class);
                break;
            case 1:
                activityIntent = new Intent(this, NoteActivity.class);
                break;
            case 2:
                activityIntent = new Intent(this, NotesListActivity.class);
                break;
            default:
                Toast.makeText(MainActivity.this, "Activity " + i + " not implemented",
                        Toast.LENGTH_SHORT).show();
                return;
        }
        startActivity(activityIntent);
    }

    @Override
    public void onClick(View v) {
        Intent loginRedirect = new Intent(this, LoginActivity.class);
        session.logoutUser();
        startActivity(loginRedirect);
        this.finish();
    }
}
