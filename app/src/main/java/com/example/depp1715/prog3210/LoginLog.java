package com.example.depp1715.prog3210;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Doug on 2017-12-15.
 */
@Entity(foreignKeys = @ForeignKey(
        entity = User.class,
        parentColumns = "id",
        childColumns = "userId"
))
public class LoginLog {
    @PrimaryKey
    @NonNull
    public String dateLoggedIn;
    public Long userId;

    public LoginLog(String dateLoggedIn, Long userId){
        this.dateLoggedIn = dateLoggedIn;
        this.userId = userId;
    }
}
