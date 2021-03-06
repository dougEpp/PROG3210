package com.example.depp1715.prog3210;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.app.Notification;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.nio.charset.StandardCharsets;

/**
 * Created by depp1715 on 11/22/2017.
 */
@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public Long id;
    public String username;
    public String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
