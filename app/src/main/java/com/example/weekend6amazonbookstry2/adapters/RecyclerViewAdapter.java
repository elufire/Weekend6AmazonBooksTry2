package com.example.weekend6amazonbookstry2.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import com.example.weekend6amazonbookstry2.R;
import com.example.weekend6amazonbookstry2.book.Book;
import com.example.weekend6amazonbookstry2.databinding.ItemBinding;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    ArrayList<Book> books;

    //Constructor for Adapter
    public RecyclerViewAdapter(ArrayList<Book> passedbooks){
        books = passedbooks;
    }


    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        //inflate the layout
        ItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.item, viewGroup, false);
        //Create the viewholder and pass the binding
        ViewHolder viewHolder = new ViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder, int position) {
        //Pass each book into each created ViewHolder
        Book book = books.get(position);
        viewHolder.itemBinding.setBook(book);
    }

    //Method for getting the ArrayList Size
    @Override
    public int getItemCount() {
        return books.size();
    }

    //Class for Viewholder
    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemBinding itemBinding;
        public ViewHolder(@NonNull ItemBinding itemBinding) {
            //Set itemBinding to the appropriate view and set the viewHolder to the appropriate view
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
    }
}
