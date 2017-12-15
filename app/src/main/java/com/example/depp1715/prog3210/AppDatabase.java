package com.example.depp1715.prog3210;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by depp1715 on 11/24/2017.
 */

@Database(entities = {User.class, Note.class, LoginLog.class
}, version = 20, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract UserDAO userDao();
    public abstract NoteDAO noteDao();
    public abstract LoginLogDAO loginLogDao();

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context, AppDatabase.class, "userdatabase")
                            .allowMainThreadQueries()
                            // recreate the database if necessary
                            .fallbackToDestructiveMigration()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
