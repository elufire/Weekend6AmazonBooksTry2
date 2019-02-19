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
    ArrayList<Book> books;

    public OkHttpHelper() {
    }

    public static void asyncOkHttpBooks(String url) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            String jsonResponse;

            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG", "onFailure: ", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                jsonResponse = response.body().string();
                Gson gson = new Gson();
                Book [] booksArray = gson.fromJson(jsonResponse, Book [].class);
                List<Book> bookList = Arrays.asList(booksArray);
                ArrayList<Book> booksArrayList = new ArrayList<>(bookList);
                if (booksArrayList != null){
                    EventBus.getDefault().post(booksArrayList);
                } else {
                    Log.d("TAG", "onBookResponse: Books did not populate");
                }

            }
        });
    }
}
