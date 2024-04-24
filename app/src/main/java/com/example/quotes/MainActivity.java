package com.example.quotes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.quotes.Api.ApiServices;
import com.example.quotes.Api.BaseUrlQuotes;
import com.example.quotes.Model.QuoteModel;
import com.example.quotes.ViewModel.Main_ViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView quoteText,quoteAuthor , next , previous;
    Main_ViewModel mainViewModel;

    private List<QuoteModel> list = new ArrayList<>();

    int LIST_ITEM = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        mainViewModel = new ViewModelProvider(MainActivity.this).get(Main_ViewModel.class);

        quoteAuthor = findViewById(R.id.quoteAuthor);
        quoteText = findViewById(R.id.quoteText);
        next = findViewById(R.id.next);
        previous = findViewById(R.id.previous);


//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });



//        mainViewModel.getQuotes(MainActivity.this).observe(MainActivity.this, new Observer<List<QuoteModel>>() {
//            @Override
//            public void onChanged(List<QuoteModel> quoteModels) {
//                list.addAll(quoteModels);
//                quoteAuthor.setText(quoteModels.get(LIST_ITEM).getAuthor());
//                quoteText.setText(quoteModels.get(LIST_ITEM).getText());
//                Log.d("List_item", "onCreate:"+ LIST_ITEM);
//            }
//        });



        mainViewModel.firstQuote(MainActivity.this).observe(this, new Observer<QuoteModel>() {
            @Override
            public void onChanged(QuoteModel quoteModel) {
                    if (quoteModel != null) {
                        setQuoto(quoteModel);

                    }
            }
        });




        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainViewModel.nextQuote(MainActivity.this).observe(MainActivity.this, new Observer<QuoteModel>() {
                    @Override
                    public void onChanged(QuoteModel quoteModel) {
                        if (quoteModel != null) {
                            setQuoto(quoteModel);

                        }
                    }
                });
            }
        });



        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainViewModel.previousQuote(MainActivity.this).observe(MainActivity.this, new Observer<QuoteModel>() {
                    @Override
                    public void onChanged(QuoteModel quoteModel) {
                        if (quoteModel != null) {
                            setQuoto(quoteModel);
                        }
                    }
                });
            }
        });
    }

    private void setQuoto(QuoteModel quoteModel) {
        quoteAuthor.setText(quoteModel.getAuthor());
        quoteText.setText(quoteModel.getText());

    }


    public void onShare(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT
               , quoteText.getText()
        );
        startActivity(intent);
    }
}