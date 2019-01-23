package com.example.roma.omdbapitest.rest;

import com.example.roma.omdbapitest.model.Movie;
import com.example.roma.omdbapitest.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {

    @GET("?apikey=1d38a50")
    Call<MovieResponse> searchMovie(@Query("s") String search, @Query("y") String tahun, @Query("type") String type);

    @GET("?apikey=1d38a50")
    Call<MovieResponse> searchMovie(@Query("s") String search, @Query("y") String tahun, @Query("type") String type, @Query("page") int page);

    @GET("?apikey=1d38a50")
    Call<Movie> detailMovie(@Query("i") String id);
}
