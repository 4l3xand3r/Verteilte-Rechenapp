package de.heffner_alexander.rechenapp;

import static org.junit.Assert.assertNotNull;

import androidx.test.core.app.ActivityScenario;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.LinkedList;

import de.heffner_alexander.rechenapp.control.GUI;

@RunWith(JUnit4.class)
public class GUITest {

    @Test
    public void loadingTest() throws InterruptedException {
        ActivityScenario.launch(MainActivity.class);

        GUI gui = new GUI();

        gui.showLoadingScreen();

        Thread.sleep(100);

        assertNotNull(LoadingActivity.loadingContext);
    }

    @Test
    public void showResultsTest() throws InterruptedException {
        ActivityScenario.launch(LoadingActivity.class);
        GUI gui = new GUI();
        gui.showResults(new LinkedList<>());

        Thread.sleep(100);

        assertNotNull(ResultsActivity.resultContext);
    }

    @Test
    public void goBackTest() throws InterruptedException {
        ActivityScenario.launch(ResultsActivity.class);
        GUI gui = new GUI();
        gui.backToBeginning();

        Thread.sleep(100);

        assertNotNull(MainActivity.mainContext);
    }

}
