package de.heffner_alexander.rechenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;


public class LoadingActivity extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    public static Context loadingContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        loadingContext = this;
    }
}