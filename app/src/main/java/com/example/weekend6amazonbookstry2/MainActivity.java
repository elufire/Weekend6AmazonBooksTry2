package com.example.weekend6amazonbookstry2;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.weekend6amazonbookstry2.adapters.RecyclerViewAdapter;
import com.example.weekend6amazonbookstry2.book.Book;
import com.example.weekend6amazonbookstry2.databinding.ActivityMainBinding;
import com.example.weekend6amazonbookstry2.viewmodels.BookViewModel;
import com.flurry.android.FlurryAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    OkHttpHelper okHttpHelper;
    ActivityMainBinding activityMainBinding;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        okHttpHelper = new OkHttpHelper();
        okHttpHelper.asyncOkHttpBooks("http://de-coding-test.s3.amazonaws.com/books.json");
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setBookViewModel(new BookViewModel(getApplication()));
        activityMainBinding.executePendingBindings();
        new FlurryAgent.Builder()
                .withLogEnabled(true)
                .build(this, "GR4H9PM5DF5Z94JRNTM7");
//        imageView = findViewById(R.id.imageView);
//        Glide.with(this).load("https://www.drscholls.com/static/media/images/drscholls/m_SC_LANDING_MODULES_750x400_Foot.jpg")
//                .into(imageView);


    }


    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getBookList(ArrayList<Book> bookList){
        Log.d("TAG", "GETBBOOKLISTRETURN: " + bookList.size() );
        activityMainBinding.rvRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(bookList);
        activityMainBinding.rvRecyclerView.setAdapter(recyclerViewAdapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getBookFromClick(Book book){
        FlurryAgent.logEvent("Recycler-Item-Clicked");
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("book", book);
        startActivity(intent);
    }
}
