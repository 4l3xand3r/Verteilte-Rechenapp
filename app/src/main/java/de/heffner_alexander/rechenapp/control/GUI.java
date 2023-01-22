package de.heffner_alexander.rechenapp.control;

import android.content.Intent;

import java.util.List;

import de.heffner_alexander.rechenapp.LoadingActivity;
import de.heffner_alexander.rechenapp.MainActivity;
import de.heffner_alexander.rechenapp.ResultsActivity;
import de.heffner_alexander.rechenapp.interfaces.IGUIController;
import kotlin.Pair;

public class GUI implements IGUIController {
    @Override
    public void showResults(List<Pair<Double, Double>> data) {
        System.out.println("Got to the end!");
        ResultsActivity.DATA.clear();
        ResultsActivity.DATA.addAll(data);
        Intent showResultsIntent = new Intent(LoadingActivity.loadingContext, ResultsActivity.class);
        LoadingActivity.loadingContext.startActivity(showResultsIntent);
    }

    @Override
    public void showLoadingScreen() {
        Intent loadingActivityIntent = new Intent(MainActivity.mainContext, LoadingActivity.class);
        MainActivity.mainContext.startActivity(loadingActivityIntent);
    }

    @Override
    public void backToBeginning() {
        Intent mainActivityIntent = new Intent(ResultsActivity.resultContext, MainActivity.class);
        ResultsActivity.resultContext.startActivity(mainActivityIntent);
    }
}
