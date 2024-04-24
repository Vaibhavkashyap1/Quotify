package com.example.quotes.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseUrlQuotes {

    private static Retrofit retrofit;

    private static final String BASE_URL = "https://type.fit/api/";

    public static Retrofit getRetrofit() {
        retrofit =new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
}

}
