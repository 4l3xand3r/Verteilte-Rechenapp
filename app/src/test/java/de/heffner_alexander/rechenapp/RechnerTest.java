package de.heffner_alexander.rechenapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.heffner_alexander.rechenapp.control.Rechner;

public class RechnerTest {

    @Test
    public void formulaCalcSuccessful() {
        Rechner rechner = new Rechner();

        boolean result = rechner.calculateFunction(
                "2+2+x",
                1,
                3,
                1
        );

        assertTrue(result);
    }

    @Test
    public void dataCorrect() {
        Rechner rechner = new Rechner();

        rechner.calculateFunction(
                "2+x",
                1,
                1,
                1
        );

        double data = rechner.fetchDataSet().get(0).component2();

        assertEquals(data, 3.0, 0.0);
    }

}
