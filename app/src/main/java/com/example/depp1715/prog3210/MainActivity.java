package com.example.depp1715.prog3210;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import com.example.depp1715.prog3210.ImageAdapter;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int numColumns;

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            numColumns = 3;
        } else {
            numColumns = 5;
        }

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
                activityIntent = new Intent(this, UtilActivity.class);
                break;
            default:
                Toast.makeText(MainActivity.this, "Activity " + i + " not implemented",
                        Toast.LENGTH_SHORT).show();
                return;
        }
        startActivity(activityIntent);
    }
}
