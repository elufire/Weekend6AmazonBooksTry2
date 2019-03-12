package com.example.weekend6amazonbookstry2.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Intent;
import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.example.weekend6amazonbookstry2.Main2Activity;
import com.example.weekend6amazonbookstry2.book.Book;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BookViewModel extends AndroidViewModel implements Observable {

    PropertyChangeRegistry propertyChangeRegistry = new PropertyChangeRegistry();

    public BookViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        propertyChangeRegistry.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        propertyChangeRegistry.remove(callback);
    }

}
