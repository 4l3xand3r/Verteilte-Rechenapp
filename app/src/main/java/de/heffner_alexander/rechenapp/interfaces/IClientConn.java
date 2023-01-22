package de.heffner_alexander.rechenapp.interfaces;

import java.util.List;

import kotlin.Pair;

public interface IClientConn {

    void initializeASAP();

    boolean sendDataToServer(List<Pair<Double, Double>> data);
}
