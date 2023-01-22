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

        assertEquals(3.0, data, 0.0);
    }

    @Test
    public void dataCorrectTwo() {
        Rechner rechner = new Rechner();

        rechner.calculateFunction(
                "1+x^2",
                2,
                2,
                1
        );

        double data = rechner.fetchDataSet().get(0).component2();

        assertEquals(5.0, data, 0.0);
    }

}
