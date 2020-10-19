package com.example.mylibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class bookActivity extends AppCompatActivity {

    private static final String TAG = "bookActivity";
    private TextView bookName, authorName, description, pageNumber;
    private ImageView bookImage;
    private Button btnCurReading, btnWantToRead, btnAlreadyRead;

    private Book incomingBook;
    private Util util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        overridePendingTransition(R.anim.in, R.anim.out);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initWidgets();

        Intent intent = getIntent();
        int id = intent.getIntExtra("bookID", 0);
        util = new Util();
        ArrayList<Book> books = util.getAllBooks();
        int s = books.size();
        for(Book b: books){
            if(b.getId() == id){

                incomingBook = b;
                bookName.setText(b.getName());
                authorName.setText(b.getAuthor());
                pageNumber.setText("Pages: " + b.getPages());
                description.setText(b.getDescription());
                Glide.with(this)
                        .asBitmap()
                        .load(b.getImageURL())
                        .into(bookImage);
            }
        }

        btnCurReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCurReadingTapped();
            }
        });

        btnWantToRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnWantToReadTapped();
            }
        });

        btnAlreadyRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAlreadyReadTapped();
            }
        });
    }

    private void btnCurReadingTapped() {
        Log.d(TAG, "btnCurReadingTapped: started");

        ArrayList<Book> currentlyReadingBooks = util.getCurrentlyReadingBooks();

        if(currentlyReadingBooks.contains(incomingBook)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("You Already added this book to your currently reading books list");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.setCancelable(false);
            builder.setMessage("You have already added this book to your Currently Reading Books");
            builder.create().show();
        }
        else{

            ArrayList<Book> wantToReadBooks = util.getWantToReadBooks();

            if(wantToReadBooks.contains(incomingBook)){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you going to read this?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        util.removeWantToReadBooks(incomingBook);
                        util.addCurrentlyReadingBooks(incomingBook);
                        Toast.makeText(bookActivity.this,"The Book " + incomingBook.getName() + " Added to your Currently Reading shelf", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create().show();
            }  else {
                ArrayList<Book> alreadyReadBooks = util.getAlreadyReadBooks();
                if(alreadyReadBooks.contains(incomingBook)){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Do you want to read this book again?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            util.removeAlreadyReadBooks(incomingBook);
                            util.addCurrentlyReadingBooks(incomingBook);
                            Toast.makeText(bookActivity.this,"The Book " + incomingBook.getName() + " Added to your Currently Reading shelf", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.create().show();
                } else {
                    util.addCurrentlyReadingBooks(incomingBook);
                    Toast.makeText(this,"The Book " + incomingBook.getName() + " Added to your Currently Reading shelf", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void btnWantToReadTapped() {
        Log.d(TAG, "btnWantToReadTapped: started");

        ArrayList<Book> wantToReadBooks = util.getWantToReadBooks();

        if(wantToReadBooks.contains(incomingBook)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.setCancelable(false);
            builder.setMessage("You have already added this book to your want to read Books");
            builder.create().show();
        }
        else{

            ArrayList<Book> alreadyReadBook = util.getAlreadyReadBooks();
            if(alreadyReadBook.contains(incomingBook)){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Do you want to read this book again?");
                builder.setTitle("Error");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        util.removeAlreadyReadBooks(incomingBook);
                        util.addWantToReadBooks(incomingBook);
                        Toast.makeText(bookActivity.this,"The Book " + incomingBook.getName() + " Added to your Want To Read shelf", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create().show();
            } else{
                ArrayList<Book> currentlyReadingBooks = util.getCurrentlyReadingBooks();
                if(currentlyReadingBooks.contains(incomingBook)){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("You already reading this book");
                    builder.setTitle("Error");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            util.removeCurrentlyReadingBooks(incomingBook);
                            util.addWantToReadBooks(incomingBook);
                            Toast.makeText(bookActivity.this,"The Book " + incomingBook.getName() + " Added to your want to read shelf", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.create().show();
                } else {
                    util.addWantToReadBooks(incomingBook);
                    Toast.makeText(this,"The Book " + incomingBook.getName() + " Added to your want to read shelf", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void btnAlreadyReadTapped() {
        Log.d(TAG, "btnAlreadyReadTapped: started");

        ArrayList<Book> alreadyReadBooks = util.getAlreadyReadBooks();

        if(alreadyReadBooks.contains(incomingBook)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.setCancelable(false);
            builder.setMessage("You have already added this book to your already read Books");
            builder.create().show();
        }
        else{
            ArrayList<Book> currentlyReadingBooks = util.getCurrentlyReadingBooks();
            if(currentlyReadingBooks.contains(incomingBook)){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("have you finished reading this book?");
                builder.setTitle("Error");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        util.removeCurrentlyReadingBooks(incomingBook);
                        util.addAlreadyReadBooks(incomingBook);
                        Toast.makeText(bookActivity.this,"The Book " + incomingBook.getName() + " Added to your Already Read shelf", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create().show();
            } else {
                ArrayList<Book> wantToReadBooks = util.getWantToReadBooks();
                if(wantToReadBooks.contains(incomingBook)){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("have you finished reading this book?");
                    builder.setTitle("Error");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            util.removeWantToReadBooks(incomingBook);
                            util.addAlreadyReadBooks(incomingBook);
                            Toast.makeText(bookActivity.this,"The Book " + incomingBook.getName() + " Added to your Already Read shelf", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.create().show();
                } else {
                    util.addAlreadyReadBooks(incomingBook);
                    Toast.makeText(this,"The Book " + incomingBook.getName() + " Added to your already read shelf", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void initWidgets() {
        bookName = findViewById(R.id.bookName);
        authorName = findViewById(R.id.authorName);
        description = findViewById(R.id.bookDesc);
        pageNumber = findViewById(R.id.bookPages);

        bookImage = findViewById(R.id.bookImage);

        btnCurReading = findViewById(R.id.btnCurReading);
        btnAlreadyRead = findViewById(R.id.btnAlreadyRead);
        btnWantToRead = findViewById(R.id.btnWantToRead);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                super.onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.close_in, R.anim.close_out);
    }

}