package de.heffner_alexander.rechenapp.control;

import android.annotation.SuppressLint;
import android.content.Intent;

import java.util.LinkedList;
import java.util.List;

import de.heffner_alexander.rechenapp.LoadingActivity;
import de.heffner_alexander.rechenapp.MainActivity;
import de.heffner_alexander.rechenapp.ResultsActivity;
import kotlin.Pair;

public class Controller {

    private static final Server server = new Server();
    private static final Client client = new Client();
    @SuppressLint("StaticFieldLeak")
    private static final Dateisystem dateisystem = new Dateisystem(ResultsActivity.resultContext);
    private static final GUI gui = new GUI();
    private static final Rechner rechner = new Rechner();

    private static final List<Pair<Double, Double>> allData = new LinkedList<>();

    public static void initASAPServer() {
        server.initializeASAP();
    }

    public static void initASAPClient() {
        client.initializeASAP();
    }

    public static void jumpToLoading() {
        gui.showLoadingScreen();
    }

    public static void returnToStart() {
        gui.backToBeginning();
    }

    public static void sendCalculations(String formula, double start, double end, double stepSize) {
        server.sendDataToClients(formula, start, end, stepSize);
    }

    public static void startCalculations(String instructions) {
        String[] splitInstructions = instructions.split("_");
        String formula = splitInstructions[0];
        double start = Double.parseDouble(splitInstructions[1]);
        double end = Double.parseDouble(splitInstructions[2]);
        double stepSize = Double.parseDouble(splitInstructions[3]);

        boolean result = rechner.calculateFunction(formula, start, end, stepSize);

        if (result) client.sendDataToServer(rechner.fetchDataSet());
    }

    public static void storeReceivedDataInMem(List<Pair<Double, Double>> data) {
        allData.addAll(data);
    }

    public static void saveDataInJSON() {
        dateisystem.saveResultsToFile(allData);
    }

    public static List<Pair<Double, Double>> getResults() {
        return rechner.fetchDataSet();
    }

    public static void showResults() {
        gui.showResults(allData);
    }

}
