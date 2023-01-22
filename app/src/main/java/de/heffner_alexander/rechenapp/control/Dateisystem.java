package de.heffner_alexander.rechenapp.control;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import de.heffner_alexander.rechenapp.ResultsActivity;
import de.heffner_alexander.rechenapp.interfaces.IDateiManager;
import kotlin.Pair;

public class Dateisystem implements IDateiManager {

    private final Context context;

    public Dateisystem(Context context) {
        this.context = context;
    }

    @Override
    public boolean saveResultsToFile(List<Pair<Double, Double>> results) {
        JSONObject jsonObject = new JSONObject();

        try {
            for (Pair<Double, Double> value : results) {
                jsonObject.put(String.valueOf(value.component1()), value.component2());
            }

            FileOutputStream fos = new FileOutputStream(
                    context.getDataDir().getAbsolutePath()
                    + "/" + LocalDateTime.now().toString() + ".json"
            );

            fos.write(jsonObject.toString().getBytes());
            fos.flush();
            fos.close();

            return true;
        } catch (JSONException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
