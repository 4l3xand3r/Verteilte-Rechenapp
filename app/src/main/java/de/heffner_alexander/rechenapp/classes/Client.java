package de.heffner_alexander.rechenapp.classes;

import android.util.Pair;

import java.util.List;

import de.heffner_alexander.rechenapp.interfaces.IClientConn;

public class Client implements IClientConn {

    @Override
    public boolean connectToServer(String host, int port) {
        return false;
    }

    @Override
    public boolean sendDataToServer(List<Pair<Double, Double>> data) {
        return false;
    }

    @Override
    public void disconnect() {

    }
}
