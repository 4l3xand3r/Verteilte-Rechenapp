package de.heffner_alexander.rechenapp.control;

import net.sharksystem.asap.ASAPEnvironmentChangesListener;
import net.sharksystem.asap.ASAPException;
import net.sharksystem.asap.ASAPHop;
import net.sharksystem.asap.ASAPMessageReceivedListener;
import net.sharksystem.asap.ASAPMessages;
import net.sharksystem.asap.ASAPPeerFS;
import net.sharksystem.asap.utils.ASAPSerialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import de.heffner_alexander.rechenapp.MainActivity;
import de.heffner_alexander.rechenapp.interfaces.IServerHost;
import kotlin.Pair;

public class Server implements IServerHost, ASAPEnvironmentChangesListener, ASAPMessageReceivedListener {

    private static final String appFormat = "heffner_alexander/rechenapp";
    private ASAPPeerFS peer;
    private final Set<CharSequence> devices = new HashSet<>();
    private int receivedDataParts = 0;
    private int sendDataParts = 0;

    @Override
    public void initializeASAP() {
        try {
            peer = new ASAPPeerFS(
                    "Server",
                    MainActivity.mainContext.getCacheDir().getAbsolutePath(),
                    Collections.singleton(appFormat)
            );
            peer.addASAPEnvironmentChangesListener(this);
            peer.addASAPMessageReceivedListener(appFormat, this);
            devices.add(peer.getPeerID());
            GUI.toast.setText("Server initialisiert und auf peer vorbereitet.");
            GUI.toast.show();
        } catch (IOException | ASAPException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean sendDataToClients(String formula, double start, double end, double stepSize) {
        if (!devices.isEmpty()) {
            double deviceSteps = (Math.abs(start) + Math.abs(end)) / devices.size();
            double lastStart = start;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            for (CharSequence device : devices) {
                String uri = "net://" + device + "_AND_" + this.peer.getPeerID() + "_converse";
                String message = formula + "_" + lastStart + "_"
                        + Math.min(lastStart + deviceSteps, end) + "_" + stepSize;
                lastStart = Math.min(lastStart + deviceSteps, end);

                if (device == peer.getPeerID()) {
                    Controller.startCalculations(message);
                    Controller.storeReceivedDataInMem(Controller.getResults());
                    sendDataParts++;
                    receivedDataParts++;
                } else {
                    try {
                        ASAPSerialization.writeCharSequenceParameter(device, bos);
                        ASAPSerialization.writeCharSequenceParameter(message, bos);
                        byte[] deserializedData = bos.toByteArray();

                        this.peer.sendOnlineASAPMessage(appFormat, uri, deserializedData);
                        sendDataParts++;
                    } catch (IOException | ASAPException e) {
                        e.printStackTrace();
                    }
                }
            }

            if (sendDataParts == receivedDataParts) {
                Controller.showResults();
            }
            return true;
        } else return false;
    }

    @Override
    public void onlinePeersChanged(Set<CharSequence> peerList) {
        for (CharSequence maybeNewPeerName : peerList) {
            CharSequence newPeerName = maybeNewPeerName;
            for (CharSequence peerName : devices) {
                if (maybeNewPeerName.toString().equalsIgnoreCase(peerName.toString())) {
                    newPeerName = null;
                    break;
                }
            }
            if (newPeerName != null) devices.add(newPeerName);
        }
    }

    @Override
    public void asapMessagesReceived(ASAPMessages asapMessages, String device, List<ASAPHop> list)
            throws IOException {

        Iterator<byte[]> iterator = asapMessages.getMessages();

        while (iterator.hasNext()) {
            ByteArrayInputStream bis = new ByteArrayInputStream(iterator.next());
            try {
                String data = ASAPSerialization.readCharSequenceParameter(bis);
                data = data.replace("Server", "");

                String[] split = data.split("--");
                List<Pair<Double, Double>> processedData = new LinkedList<>();

                for (String dataEntry : split) {
                    String[] entrySplit = dataEntry.split("~");
                    processedData.add(
                            new Pair<>(Double.parseDouble(entrySplit[0]),
                                    Double.parseDouble(entrySplit[1])
                            )
                    );
                }

                Controller.storeReceivedDataInMem(processedData);
                receivedDataParts++;

                if (sendDataParts == receivedDataParts) Controller.showResults();
            } catch (ASAPException e) {
                e.printStackTrace();
            }
        }
    }
}
