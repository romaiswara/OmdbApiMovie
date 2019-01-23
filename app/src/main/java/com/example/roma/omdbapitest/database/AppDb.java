package com.example.roma.omdbapitest.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.roma.omdbapitest.model.Movie;

@Database(entities = Movie.class, version = 1, exportSchema = false)
public abstract class AppDb extends RoomDatabase {
    public abstract InterfaceMovie movie();

    private static volatile AppDb INSTANCE;

    public static AppDb getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (AppDb.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDb.class, "movie-room.db")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

