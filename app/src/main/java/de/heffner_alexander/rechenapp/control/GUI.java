package de.heffner_alexander.rechenapp.control;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;

import java.util.List;

import de.heffner_alexander.rechenapp.LoadingActivity;
import de.heffner_alexander.rechenapp.MainActivity;
import de.heffner_alexander.rechenapp.ResultsActivity;
import de.heffner_alexander.rechenapp.interfaces.IGUIController;
import kotlin.Pair;

public class GUI implements IGUIController {

    private final Handler handler = new Handler(Looper.getMainLooper());

    private final Runnable loadingJump = () -> {
        Intent showResultsIntent = new Intent(LoadingActivity.loadingContext, ResultsActivity.class);
        showResultsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        LoadingActivity.loadingContext.startActivity(showResultsIntent);
    };

    @Override
    public void showResults(List<Pair<Double, Double>> data) {
        System.out.println("Got to the end!");
        ResultsActivity.DATA.clear();
        ResultsActivity.DATA.addAll(data);
        for (Pair<Double, Double> entry : data) {
            System.out.println("x: " + entry.component1() + " || y: " + entry.component2());
        }
        if (LoadingActivity.loadingContext == null) handler.postDelayed(loadingJump, 500);
        else handler.post(loadingJump);
    }

    @Override
    public void showLoadingScreen() {
        Intent loadingActivityIntent = new Intent(MainActivity.mainContext, LoadingActivity.class);
        loadingActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        MainActivity.mainContext.startActivity(loadingActivityIntent);
    }

    @Override
    public void backToBeginning() {
        ResultsActivity.DATA.clear();
        Intent mainActivityIntent = new Intent(ResultsActivity.resultContext, MainActivity.class);
        mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        ResultsActivity.resultContext.startActivity(mainActivityIntent);
    }


}
