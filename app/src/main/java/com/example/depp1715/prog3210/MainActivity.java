package com.example.depp1715.prog3210;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    private UserSessionManager session;
    private User user;
    private Button btnLogout;
    private Button btnContact;
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
        btnContact = (Button) findViewById(R.id.btnContact);
        btnLogout.setOnClickListener(this);
        btnContact.setOnClickListener(this);

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
            case 3:
                activityIntent = new Intent(this, TipCalcActivity.class);
                break;
            case 4:
                activityIntent = new Intent(this, LoginListActivity.class);
                break;
            case 5:
                Intent audioServiceIntent = new Intent(this, AudioIntentService.class);
                startService(audioServiceIntent);
                return;
            case 6:
                activityIntent = new Intent(this, WebImageActivity.class);
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
        if (v.getId() == R.id.btnLogout) {
            Intent loginRedirect = new Intent(this, LoginActivity.class);
            loginRedirect.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            session.logoutUser();
            startActivity(loginRedirect);
            this.finish();
        } else if (v.getId() == R.id.btnContact){
            Intent actionIntent = new Intent(Intent.ACTION_SENDTO);

            String[] addresses = {"depp1715@conestogac.on.ca"};
            actionIntent.setData(Uri.parse("mailto:"));
            actionIntent.putExtra(Intent.EXTRA_EMAIL, addresses);
            actionIntent.putExtra(Intent.EXTRA_SUBJECT, "Awesome App");

            if (actionIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(actionIntent);
            } else {
                Toast toast = Toast.makeText(MainActivity.this, "No email app found.", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
}
