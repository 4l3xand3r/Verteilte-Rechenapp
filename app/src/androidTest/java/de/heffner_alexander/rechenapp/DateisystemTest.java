package de.heffner_alexander.rechenapp;

import static org.junit.Assert.assertTrue;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.LinkedList;
import java.util.List;

import de.heffner_alexander.rechenapp.control.Dateisystem;
import kotlin.Pair;

@RunWith(AndroidJUnit4.class)
public class DateisystemTest {

    @Test
    public void generateFileTest() {
        Dateisystem dateisystem = new Dateisystem(InstrumentationRegistry.getInstrumentation().getTargetContext());

        List<Pair<Double, Double>> test = new LinkedList<>();

        test.add(new Pair<>(1.0, 1.0));
        test.add(new Pair<>(1.5, 2.0));

        assertTrue(dateisystem.saveResultsToFile(test));
    }

}
