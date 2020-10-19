package com.example.mylibrary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Button buttonAllBooks, buttonCurrentlyReadingBooks, buttonWantToReadBooks, buttonAlreadyReadBooks, buttonAbout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        overridePendingTransition(R.anim.in, R.anim.out);

        initWidgets();
        setOnClickListener();
    }

    private void setOnClickListener(){
        buttonAllBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,
                        allBooksActivity.class);
                startActivity(intent);
            }
        });

        buttonCurrentlyReadingBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,
                        currentlyReadingActivity.class);
                startActivity(intent);
            }
        });

        buttonAlreadyReadBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,
                        alreadyReadActivity.class);
                startActivity(intent);
            }
        });

        buttonWantToReadBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,
                        WantToActivity.class);
                startActivity(intent);
            }
        });

        buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aboutTapped();
            }
        });
    }

    private void aboutTapped() {
        Log.d(TAG, "aboutTapped: started");

        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("About my library")
                .setMessage("Build and published by Aryan\n" +
                        "\n"+
                        "If you want to hire me or \n"+
                        "If you want to check my other work\n"+
                        "Take a look at: \n"+
                        "xyz")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(MainActivity.this, myWebViewActivity.class);
                        intent.putExtra("url", "https://www.youtube.com");
                        startActivity(intent);
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        builder.create().show();
    }

    private void initWidgets(){
        buttonAllBooks = findViewById(R.id.buttonAllBooks);
        buttonCurrentlyReadingBooks = findViewById(R.id.buttonCurrentlyReadingBooks);
        buttonAlreadyReadBooks = findViewById(R.id.buttonAlreadyReadBooks);
        buttonWantToReadBooks = findViewById(R.id.buttonWantToReadBooks);
        buttonAbout = findViewById(R.id.buttonAbout);
    }
    @Override
    public void finish() {

    }
}