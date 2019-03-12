package com.example.weekend6amazonbookstry2;

import android.util.Log;

import com.example.weekend6amazonbookstry2.book.Book;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpHelper {

    public OkHttpHelper() {
    }

    //Asychronous Http call
    public static void asyncOkHttpBooks(String url) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            String jsonResponse;

            @Override
            public void onFailure(Call call, IOException e) {
                //Log the failure
                Log.e("TAG", "onFailure: ", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //After successful call get the json response as a string
                jsonResponse = response.body().string();

                //Parse through the string with Gson and convert into an array of books
                Gson gson = new Gson();
                Book [] booksArray = gson.fromJson(jsonResponse, Book [].class);

                //Converted the array to an Arraylist for use with the recycler view adapter I will be using
                List<Book> bookList = Arrays.asList(booksArray);
                ArrayList<Book> booksArrayList = new ArrayList<>(bookList);

                //Send the Arraylist to the MainActivity with Eventbus
                if (booksArrayList != null){
                    EventBus.getDefault().post(booksArrayList);
                } else {
                    Log.d("TAG", "onBookResponse: Books did not populate");
                }
            }
        });
    }
}
