package de.heffner_alexander.rechenapp.interfaces;

public interface IServerHost {

    void initializeASAP();
    boolean sendDataToClients(String formula, double start, double end, double stepSize);

}
