package com.example.quotes.ViewModel;


import android.app.Activity;
import android.util.Log;
import android.widget.Toast;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import com.example.quotes.Api.ApiServices;
import com.example.quotes.Api.BaseUrlQuotes;
import com.example.quotes.Model.QuoteModel;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class Main_ViewModel extends ViewModel {

    ApiServices apiServices = BaseUrlQuotes.getRetrofit().create(ApiServices.class);

    private List<QuoteModel> list = new ArrayList<>();

    public int CURRENT_ITEM = 0;

    private MutableLiveData<QuoteModel> singlequote;



    public LiveData<QuoteModel> firstQuote(Activity activity){
      if (singlequote == null) {
          singlequote = new MutableLiveData<>();
          singlequotes(activity , CURRENT_ITEM);
      }
      return singlequote;
  }
    private void singlequotes(Activity activity , int CURRENT_ITEM1) {
        apiServices.getQuotes().enqueue(new Callback<List<QuoteModel>>() {
            @Override
            public void onResponse(Call<List<QuoteModel>> call, Response<List<QuoteModel>> response) {
                singlequote.setValue(response.body().get(CURRENT_ITEM1));
            }
            @Override
            public void onFailure(Call<List<QuoteModel>> call, Throwable t) {
                Toast.makeText(activity, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public LiveData<QuoteModel> nextQuote(Activity activity) {
        if (CURRENT_ITEM != 15){
            CURRENT_ITEM++;
        singlequote = new MutableLiveData<>();
        singlequotes(activity, CURRENT_ITEM);
    }
        else
            Toast.makeText(activity, "This is the Last quote", Toast.LENGTH_SHORT).show();
        return singlequote;
    }
    public LiveData<QuoteModel> previousQuote(Activity activity){

            if (CURRENT_ITEM!=0) {
                CURRENT_ITEM--;
                singlequote = new MutableLiveData<>();
                singlequotes(activity , CURRENT_ITEM);
        }
            else
                Toast.makeText(activity, "This is the first quote", Toast.LENGTH_SHORT).show();
        return singlequote;
    }
}
