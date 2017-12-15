package com.example.depp1715.prog3210;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class NoteActivity extends AppCompatActivity implements View.OnClickListener {
    private UserSessionManager session;
    private EditText txtNoteText;
    private Button btnSave;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        session = new UserSessionManager(getApplicationContext());
        database = AppDatabase.getDatabase(getApplicationContext());
        txtNoteText = (EditText) findViewById(R.id.txtNoteText);
        btnSave = (Button) findViewById(R.id.btnSaveNote);

        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String noteText = txtNoteText.getText().toString();
        if(noteText.length() > 0){
            Note note = new Note(Integer.parseInt(session.getUserDetails().get(UserSessionManager.KEY_ID)), noteText);
            database.noteDao().addNote(note);
            Intent notesListIntent = new Intent(this, NotesListActivity.class);
            startActivity(notesListIntent);
            this.finish();
        }
    }
}
