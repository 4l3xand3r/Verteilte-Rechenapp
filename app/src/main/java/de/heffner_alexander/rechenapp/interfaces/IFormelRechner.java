package de.heffner_alexander.rechenapp.interfaces;

import java.util.List;

import kotlin.Pair;

public interface IFormelRechner {

    boolean calculateFunction(String formula, double start, double end, double stepSize);
    List<Pair<Double, Double>> fetchDataSet();

}
