package com.example.depp1715.prog3210;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditNoteActivity extends AppCompatActivity implements View.OnClickListener {
    private AppDatabase database;
    private Note note;
    private long noteId;
    private String noteText;
    private EditText txtEditNote;
    private Button btnSaveNoteEdit;
    private Button btnDeleteNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        database = AppDatabase.getDatabase(getApplicationContext());
        noteId = getIntent().getExtras().getLong("NoteId");
        note = database.noteDao().getNote(noteId);
        noteText = note.text;

        txtEditNote = (EditText) findViewById(R.id.txtEditNoteText);
        txtEditNote.setText(noteText);

        btnSaveNoteEdit = (Button) findViewById(R.id.btnSaveNoteEdit);
        btnSaveNoteEdit.setOnClickListener(this);
        btnDeleteNote = (Button) findViewById(R.id.btnDeleteNote);
        btnDeleteNote.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSaveNoteEdit) {
            String newNoteText = txtEditNote.getText().toString();
            note.text = newNoteText;
            database.noteDao().updateNote(note);
        } else if (v.getId() == R.id.btnDeleteNote){
            database.noteDao().removeNote(noteId);
        }

        Intent notesListIntent = new Intent(EditNoteActivity.this, NotesListActivity.class);
        startActivity(notesListIntent);
        this.finish();
    }
}
