package de.heffner_alexander.rechenapp.interfaces;

import java.io.File;
import java.util.List;

public interface IDateiManager {

    File saveResultsToFile(List<Double> results);

}
