package de.heffner_alexander.rechenapp.classes;

import net.sharksystem.asap.ASAPException;
import net.sharksystem.asap.ASAPPeerFS;
import net.sharksystem.asap.utils.ASAPSerialization;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import de.heffner_alexander.rechenapp.MainActivity;
import de.heffner_alexander.rechenapp.interfaces.IServerHost;

public class Server implements IServerHost {

    private static final String appFormat = "heffner_alexander/rechenapp";
    private static final String appURI = "net://server";
    private ASAPPeerFS peer;
    private final List<String> devices = new LinkedList<>();

    @Override
    public void openServer() {
        try {
            peer = new ASAPPeerFS(
                    "Server",
                    MainActivity.mainContext.getCacheDir().getAbsolutePath(),
                    Collections.singleton(appFormat)
            );
        } catch (IOException | ASAPException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean sendDataToClients(String formula, int start, int end, int stepSize) {
        if (!devices.isEmpty()) {
            int deviceSteps = (Math.abs(start) + Math.abs(end)) / devices.size();
            int lastStart = start;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            for (String device : devices) {
                String message = formula + "_" + lastStart + "_"
                        + Math.min(lastStart + deviceSteps, end) + "_" + stepSize;
                lastStart = Math.min(lastStart + deviceSteps, end);

                try {
                    ASAPSerialization.writeCharSequenceParameter(device, bos);
                    ASAPSerialization.writeCharSequenceParameter(message, bos);
                    byte[] deserializedData = bos.toByteArray();

                    this.peer.sendOnlineASAPMessage(appFormat, appURI, deserializedData);
                } catch (IOException | ASAPException e) {
                    e.printStackTrace();
                }
            }
            return true;
        } else return false;
    }

    @Override
    public void closeConnection() {

    }
}
