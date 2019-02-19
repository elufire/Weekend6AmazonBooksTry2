package com.example.weekend6amazonbookstry2;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.weekend6amazonbookstry2.book.Book;
import com.example.weekend6amazonbookstry2.databinding.ActivityMain2Binding;


public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        Book book = intent.getParcelableExtra("book");
        ActivityMain2Binding activityMainBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_main2);
        activityMainBinding.setBookDisplay(book);
        activityMainBinding.executePendingBindings();
    }
}
