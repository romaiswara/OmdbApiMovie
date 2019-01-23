package com.example.roma.omdbapitest.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {
    @SerializedName("Search")
    List<Movie> movieList;
    @SerializedName("totalResults")
    int totalResult;
    @SerializedName("Response")
    boolean response;

    public MovieResponse(List<Movie> movieList, int totalResult, boolean response) {
        this.movieList = movieList;
        this.totalResult = totalResult;
        this.response = response;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }
}
