package de.heffner_alexander.rechenapp.interfaces;

public interface IServerHost {

    void openServer();
    boolean sendDataToClients(String formula, int start, int end, int stepSize);
    void closeConnection();

}
