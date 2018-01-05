package com.example.depp1715.prog3210;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class NotesListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private UserSessionManager session;
    private AppDatabase database;
    private List<Note> notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);
        session = new UserSessionManager(getApplicationContext());
        database = AppDatabase.getDatabase(getApplicationContext());

        notes = database.noteDao().getNotesForUser(Integer.parseInt(session.getUserDetails().get(UserSessionManager.KEY_ID)));
        String[] notesArray = new String[notes.size()];
        long[] noteIds = new long[notes.size()];
        for (int i = 0; i < notes.size(); i++){
            if (notes.get(i).text.length() > 20){
                notesArray[i] = notes.get(i).text.substring(0, 19) + "...";
            } else {
                notesArray[i] = notes.get(i).text;
            }
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, notesArray);
        ListView listView = (ListView)findViewById(R.id.lblNotesList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        //TODO: click item to edit note
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent editNoteIntent = new Intent(this, EditNoteActivity.class);
        editNoteIntent.putExtra("NoteId", notes.get(position).id);
//        Toast.makeText(NotesListActivity.this, position + ": " + id,
//                Toast.LENGTH_SHORT).show();
        startActivity(editNoteIntent);
    }
}
