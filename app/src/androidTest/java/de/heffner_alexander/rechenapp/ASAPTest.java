package de.heffner_alexander.rechenapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import androidx.test.core.app.ActivityScenario;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import de.heffner_alexander.rechenapp.control.Client;
import de.heffner_alexander.rechenapp.control.Controller;
import de.heffner_alexander.rechenapp.control.Server;

@RunWith(JUnit4.class)
public class ASAPTest {

    @Test
    public void serverAndClientTestOne() {
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);

        Server server = new Server();
        Client client = new Client();

        server.initializeASAP();
        client.initializeASAP();

        server.sendDataToClients(
                "1+x",
                1.0,
                1.0,
                1.0
        );

        assertNotNull(Controller.getResults());

        scenario.close();
    }

    @Test
    public void serverAndClientTestTwo() {
        ActivityScenario<MainActivity> activity = ActivityScenario.launch(MainActivity.class);
        ActivityScenario<LoadingActivity> loadingActivity = ActivityScenario.launch(LoadingActivity.class);
        ActivityScenario<ResultsActivity> resultsActivity = ActivityScenario.launch(ResultsActivity.class);

        Server server = new Server();
        Client client = new Client();

        server.initializeASAP();
        client.initializeASAP();

        server.initializeASAP();
        client.initializeASAP();

        server.sendDataToClients(
                "2+x",
                1.0,
                1.0,
                1.0
        );

        client.sendDataToServer(Controller.getResults());

        System.out.println(Controller.getResults().size());

        assertEquals(2, Controller.getResults().size(), 0.0);

        activity.close();
        loadingActivity.close();
        resultsActivity.close();
    }

}
