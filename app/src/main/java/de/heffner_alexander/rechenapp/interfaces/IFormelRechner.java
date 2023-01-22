package de.heffner_alexander.rechenapp.interfaces;

import java.util.List;

import kotlin.Pair;

public interface IFormelRechner {

    boolean calculateFunction(String formula, int start, int end, double stepSize);
    List<Pair<Double, Double>> fetchDataSet();

}
