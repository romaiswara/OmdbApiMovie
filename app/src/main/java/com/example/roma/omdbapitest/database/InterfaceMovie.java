package com.example.roma.omdbapitest.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.roma.omdbapitest.model.Movie;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface InterfaceMovie {
    @Insert(onConflict = REPLACE)
    void insertMovie(Movie movie);

    @Query("SELECT * FROM Movie WHERE imdbID = :idMovie LIMIT 1")
    Movie getById(String idMovie);

    @Query("DELETE FROM Movie WHERE imdbID = :idMovie")
    void deleteById(String idMovie);

    @Query("SELECT * FROM MOVIE")
    List<Movie> getAllMovie();
}
