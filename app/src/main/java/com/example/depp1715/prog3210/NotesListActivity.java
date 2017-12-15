package com.example.depp1715.prog3210;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class NotesListActivity extends AppCompatActivity {
    private UserSessionManager session;
    private AppDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);
        session = new UserSessionManager(getApplicationContext());
        database = AppDatabase.getDatabase(getApplicationContext());

        List<Note> notes = database.noteDao().getNotesForUser(Integer.parseInt(session.getUserDetails().get(UserSessionManager.KEY_ID)));
        String[] notesArray = new String[notes.size()];
        for (int i = 0; i < notes.size(); i++){
            if (notes.get(i).text.length() > 20){
                notesArray[i] = notes.get(i).text.substring(0, 24) + "...";
            } else {
                notesArray[i] = notes.get(i).text;
            }
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, notesArray);
        ListView listView = (ListView)findViewById(R.id.lblNotesList);
        listView.setAdapter(adapter);
        //TODO: click item to edit note
    }
}
