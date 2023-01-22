package de.heffner_alexander.rechenapp.interfaces;

import android.util.Pair;

import java.util.List;

public interface IClientConn {

    boolean connectToServer(String host, int port);
    boolean sendDataToServer(List<Pair<Double, Double>> data);
    void disconnect();

}
