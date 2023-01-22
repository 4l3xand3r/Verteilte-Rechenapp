package de.heffner_alexander.rechenapp;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.LinkedList;
import java.util.List;

import de.heffner_alexander.rechenapp.control.listeners.ReturnListener;
import de.heffner_alexander.rechenapp.control.listeners.SaveResultsListener;
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

        saveResultsButton.setOnClickListener(new SaveResultsListener());
        returnButton.setOnClickListener(new ReturnListener());

        LineChart lineChart = findViewById(R.id.data_chart);

        List<Entry> entries = new LinkedList<>();

        for (Pair<Double, Double> entry : DATA) {
            entries.add(new Entry(entry.component1().floatValue(), entry.component2().floatValue()));
        }

        LineData dataSet = new LineData(new LineDataSet(entries, "Resultate"));

        lineChart.setContentDescription("Berechnete Werte der Funktion");
        lineChart.setData(dataSet);
        lineChart.invalidate();
    }
}