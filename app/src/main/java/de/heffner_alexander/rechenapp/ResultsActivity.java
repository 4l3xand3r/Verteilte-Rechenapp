package de.heffner_alexander.rechenapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import java.util.LinkedList;
import java.util.List;

import kotlin.Pair;

public class ResultsActivity extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    public static Context resultContext;

    public static final List<Pair<Double, Double>> DATA = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        resultContext = this;


        Button saveResultsButton = findViewById(R.id.save_button);
        Button returnButton = findViewById(R.id.back_button);
    }
}