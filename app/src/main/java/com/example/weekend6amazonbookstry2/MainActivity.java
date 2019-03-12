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
    //Declare OkHttp helper and activity binding
    OkHttpHelper okHttpHelper;
    ActivityMainBinding activityMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set the layout to inflate to and the viewmodel for this activity
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setBookViewModel(new BookViewModel(getApplication()));
        activityMainBinding.executePendingBindings();

        //initialize the okhttp helper, make the api call passing the amazon url
        okHttpHelper = new OkHttpHelper();
        okHttpHelper.asyncOkHttpBooks("http://de-coding-test.s3.amazonaws.com/books.json");



        //Build the Flurry agent for gathering information on user activity
        new FlurryAgent.Builder()
                .withLogEnabled(true)
                .build(this, "GR4H9PM5DF5Z94JRNTM7");
    }

//Register and subscribe to Eventbus for this activity
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

    //Listen for incoming arrayList of books an main thread
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getBookList(ArrayList<Book> bookList){
        Log.d("TAG", "GETBBOOKLISTRETURN: " + bookList.size() );
        activityMainBinding.rvRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(bookList);
        activityMainBinding.rvRecyclerView.setAdapter(recyclerViewAdapter);
    }

    //Listen for incoming Books after a book is clicked from recycler view
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getBookFromClick(Book book){
        FlurryAgent.logEvent("Recycler-Item-Clicked");
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("book", book);
        startActivity(intent);
    }
}
