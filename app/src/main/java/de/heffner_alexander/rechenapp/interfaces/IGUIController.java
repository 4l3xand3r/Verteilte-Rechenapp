package de.heffner_alexander.rechenapp.interfaces;

import java.util.List;

import kotlin.Pair;

public interface IGUIController {

    void showResults(List<Pair<Double, Double>> data);
    void showLoadingScreen();
    void backToBeginning();

}
