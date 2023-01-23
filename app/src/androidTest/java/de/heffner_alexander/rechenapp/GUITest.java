package de.heffner_alexander.rechenapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertNotNull;

import android.os.Looper;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.intent.Intents;

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
        Looper.prepare();
        ActivityScenario.launch(ResultsActivity.class);
        GUI gui = new GUI();
        gui.backToBeginning();

        Thread.sleep(100);

        assertNotNull(MainActivity.mainContext);
    }

    @Test
    public void espressoTestOne() throws InterruptedException {
        Intents.init();
        ActivityScenario<MainActivity> mainScenario = ActivityScenario.launch(MainActivity.class);

        onView(withId(R.id.main_formula_input)).perform(typeText("2*x"));
        onView(withId(R.id.start_input)).perform(typeText("1"));
        onView(withId(R.id.end_input)).perform(typeText("1"));
        onView(withId(R.id.steps_input)).perform(typeText("1"));
        onView(withId(R.id.calculate_button)).perform(click());

        Thread.sleep(100);

        intended(hasComponent(LoadingActivity.class.getName()));
        mainScenario.close();
    }

}
