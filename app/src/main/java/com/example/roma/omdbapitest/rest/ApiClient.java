package com.example.roma.omdbapitest.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public final static String BASE_URL = "http://www.omdbapi.com/";
    public final static String API_KEY = "1d38a50";

    private static Retrofit retrofit=null;

    public static Retrofit getRetrofit() {
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
