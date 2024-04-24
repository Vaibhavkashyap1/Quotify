package com.example.quotes.Api;

import com.example.quotes.Model.QuoteModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServices {
    @GET("quotes")
    Call<List<QuoteModel>> getQuotes();
}
