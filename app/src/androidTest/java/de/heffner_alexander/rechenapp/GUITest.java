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
        Intents.init();
        ActivityScenario<MainActivity> activityScenario = ActivityScenario.launch(MainActivity.class);

        GUI gui = new GUI();

        gui.showLoadingScreen();

        Thread.sleep(100);

        intended(hasComponent(LoadingActivity.class.getName()));
        activityScenario.close();
        Intents.release();
    }

    @Test
    public void showResultsTest() throws InterruptedException {
        Intents.init();
        ActivityScenario<LoadingActivity> loadingScenario = ActivityScenario.launch(LoadingActivity.class);
        GUI gui = new GUI();
        gui.showResults(new LinkedList<>());

        Thread.sleep(100);

        intended(hasComponent(ResultsActivity.class.getName()));
        loadingScenario.close();
        Intents.release();
    }

    @Test
    public void goBackTest() throws InterruptedException {
        ActivityScenario<ResultsActivity> resultScenario = ActivityScenario.launch(ResultsActivity.class);
        Looper.prepare();
        GUI gui = new GUI();
        gui.backToBeginning();
        System.out.println("Ran Test");

        Thread.sleep(200);

        assertNotNull(MainActivity.mainContext);
        resultScenario.close();
        Looper.myLooper().quitSafely();
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
        Intents.release();
    }

}
