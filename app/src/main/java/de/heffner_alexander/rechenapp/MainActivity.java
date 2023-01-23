package de.heffner_alexander.rechenapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import de.heffner_alexander.rechenapp.control.listeners.ClientConnectListener;
import de.heffner_alexander.rechenapp.control.listeners.ServerOnClickListener;
import de.heffner_alexander.rechenapp.control.listeners.StartCalculationListener;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    public static Context mainContext;

    @Override
    protected void onStart() {
        super.onStart();
        mainContext = this;
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button serverButton = findViewById(R.id.server_button);
        Button connectButton = findViewById(R.id.connect_button);
        Button startButton = findViewById(R.id.calculate_button);

        TextView formula = findViewById(R.id.main_formula_input);
        TextView start = findViewById(R.id.start_input);
        TextView end = findViewById(R.id.end_input);
        TextView stepSize = findViewById(R.id.steps_input);

        serverButton.setOnClickListener(new ServerOnClickListener());
        connectButton.setOnClickListener(new ClientConnectListener());
        startButton.setOnClickListener(new StartCalculationListener(
                formula, start, end, stepSize
        ));
    }
}