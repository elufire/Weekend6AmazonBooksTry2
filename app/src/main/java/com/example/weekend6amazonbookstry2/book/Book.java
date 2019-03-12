
package com.example.weekend6amazonbookstry2.book;

import android.content.Intent;
import android.databinding.BindingAdapter;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.weekend6amazonbookstry2.Main2Activity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.eventbus.EventBus;

public class Book implements Parcelable
{

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("imageURL")
    @Expose
    private String imageURL;
    public final static Creator<Book> CREATOR = new Creator<Book>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        public Book[] newArray(int size) {
            return (new Book[size]);
        }

    }
    ;

    protected Book(Parcel in) {
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.author = ((String) in.readValue((String.class.getClassLoader())));
        this.imageURL = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Book() {
    }

    /**
     * 
     * @param author
     * @param title
     * @param imageURL
     */
    public Book(String title, String author, String imageURL) {
        super();
        this.title = title;
        this.author = author;
        this.imageURL = imageURL;
    }

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .apply(new RequestOptions().override(400, 400))
                .into(view);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void onClick(View view, Book book){
        Log.d("TAG", "IN ONCLICK" + book.getTitle());

        Intent intent = new Intent(view.getContext(), Main2Activity.class);
        intent.putExtra("book", book);
        view.getContext().startActivity(intent);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(title);
        dest.writeValue(author);
        dest.writeValue(imageURL);
    }

    public int describeContents() {
        return  0;
    }

}
