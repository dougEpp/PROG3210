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
    @PrimaryKey
    public final int id;
    public String username;
    public String password;

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
