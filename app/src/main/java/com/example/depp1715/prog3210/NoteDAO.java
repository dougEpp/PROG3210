package com.example.depp1715.prog3210;

/**
 * Created by Doug on 2017-12-15.
 */

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import java.util.List;

@Dao
public interface NoteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addNote(Note note);

    @Query("select * from note where userId = :userId")
    public List<Note> getNotesForUser(long userId);

    @Query("select * from note where id = :noteId")
    public Note getNote(long noteId);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateNote(Note note);

    @Query("delete from note where id = :noteId")
    void removeNote(long noteId);
}
