package com.example.mylibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Objects;

public class allBooksActivity extends AppCompatActivity {
    private static final String TAG = "allBooksActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);

        overridePendingTransition(R.anim.in, R.anim.out);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Log.d(TAG, "onCreate: started");

        RecyclerView booksRecView = findViewById(R.id.booksRecView);

        BooksRecViewAdapter adapter = new BooksRecViewAdapter(this);
        booksRecView.setAdapter(adapter);
        booksRecView.setLayoutManager(new GridLayoutManager(this, 2));

//        ArrayList<Book> books = new ArrayList<>();

//        books.add(new Book("Data and Golaith", "Bruce Schneier", 207,
//                "https://m.media-amazon.com/images/I/51rddzqd88L.jpg",
//                "Rich Dad Poor Dad is about Robert Kiyosaki and his two dads—his real father (poor dad) and the father of his best friend (rich dad)—and the ways in which both men shaped his thoughts about money and investing."));
//
//        books.add(new Book("The Art of Invisibility", "Kevin Mitnick", 320,
//                "https://images-na.ssl-images-amazon.com/images/I/41w3qUAvejL._SX332_BO1,204,203,200_.jpg",
//                "Kevin Mitnick, the world's most famous hacker, teaches you easy cloaking and counter-measures for citizens and consumers in the age of Big Brother and Big Data."));
//
//        books.add(new Book("Permanent Record", "Edward Snowden", 352,
//                "https://m.media-amazon.com/images/I/51ib1G1AKcL.jpg",
//                "Permanent Record is a 2019 autobiography by Edward Snowden, whose revelations sparked a global debate about surveillance."));

        Util util = new Util();
        ArrayList<Book> books;
        books = Util.getAllBooks();
        adapter.setBooks(books);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.close_in, R.anim.close_out);
    }
}