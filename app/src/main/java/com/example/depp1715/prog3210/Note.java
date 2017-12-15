package com.example.depp1715.prog3210;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.app.Notification;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.nio.charset.StandardCharsets;

/**
 * Created by depp1715 on 11/22/2017.
 */
@Entity(foreignKeys = @ForeignKey(
        entity = User.class,
        parentColumns = "id",
        childColumns = "userId"
))
public class Note {
    @PrimaryKey(autoGenerate = true)
    public Long id;
    public int userId;
    public String text;

    public Note(int userId, String text) {
        this.userId = userId;
        this.text = text;
    }
}
