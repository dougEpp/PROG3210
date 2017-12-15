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
public interface LoginLogDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addLogin(LoginLog login);

    @Query("select * from loginlog")
    public List<LoginLog> getLogins();

    @Query("select * from loginlog where userId = :userId")
    public List<LoginLog> getLoginsForUser(long userId);

    @Query("delete from loginlog where dateLoggedIn = :date")
    void removeLoginLog(String date);
}
