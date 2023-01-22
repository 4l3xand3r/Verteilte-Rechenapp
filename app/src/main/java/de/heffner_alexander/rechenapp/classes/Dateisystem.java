package de.heffner_alexander.rechenapp.classes;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.List;

import de.heffner_alexander.rechenapp.interfaces.IDateiManager;

public class Dateisystem implements IDateiManager {
    @Override
    public File saveResultsToFile(List<Double> results) {
        JSONObject jsonObject = new JSONObject();
        for (double value : results) {

        }

        return null;
    }
}
