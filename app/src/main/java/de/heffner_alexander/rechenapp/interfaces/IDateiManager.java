package de.heffner_alexander.rechenapp.interfaces;

import java.io.File;
import java.util.List;

import kotlin.Pair;

public interface IDateiManager {

    boolean saveResultsToFile(List<Pair<Double, Double>> results);

}
