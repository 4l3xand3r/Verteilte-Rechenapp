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
import java.util.List;
import java.util.Set;
import java.util.UUID;

import de.heffner_alexander.rechenapp.MainActivity;
import de.heffner_alexander.rechenapp.interfaces.IClientConn;
import kotlin.Pair;

public class Client implements IClientConn, ASAPEnvironmentChangesListener, ASAPMessageReceivedListener {

    private static final String appFormat = "heffner_alexander/rechenapp";
    private ASAPPeerFS peer;
    private final Set<CharSequence> devices = new HashSet<>();


    @Override
    public void initializeASAP() {
        try {
            peer = new ASAPPeerFS(
                    UUID.randomUUID().toString(),
                    MainActivity.mainContext.getCacheDir().getAbsolutePath(),
                    Collections.singleton(appFormat)
            );
            peer.addASAPEnvironmentChangesListener(this);
            peer.addASAPMessageReceivedListener(appFormat, this);
        } catch (IOException | ASAPException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean sendDataToServer(List<Pair<Double, Double>> data) {
        boolean successful = false;

        for (CharSequence device : devices) {
            if (device == "Server") {
                String uri = this.peer.getPeerID().toString() + "_AND_" + device + "_CONVERSE";
                StringBuilder builder = new StringBuilder();

                for (Pair<Double, Double> dataEntry : data) {
                    builder.append(dataEntry.component1()).append("~").append(dataEntry.component2()).append("--");
                }

                builder.replace(builder.length() - 2, builder.length(), "");

                try {
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    ASAPSerialization.writeCharSequenceParameter(device, bos);
                    ASAPSerialization.writeCharSequenceParameter(builder.toString(), bos);
                    peer.sendOnlineASAPMessage(appFormat, uri, bos.toByteArray());
                    successful = true;
                } catch (IOException | ASAPException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }

        return successful;
    }


    @Override
    public void onlinePeersChanged(Set<CharSequence> peers) {
        for (CharSequence maybeNewPeer : peers) {
            CharSequence newPeer = maybeNewPeer;
            for (CharSequence device : devices) {
                if (device.toString().equalsIgnoreCase(maybeNewPeer.toString())) {
                    newPeer = null;
                    break;
                }
            }
            if (newPeer != null) devices.add(newPeer);
        }
    }

    @Override
    public void asapMessagesReceived(ASAPMessages asapMessages, String sender, List<ASAPHop> list)
            throws IOException {

        if (sender.equalsIgnoreCase("server")) {
            Iterator<byte[]> messages = asapMessages.getMessages();

            while (messages.hasNext()) {
                ByteArrayInputStream bis = new ByteArrayInputStream(messages.next());
                try {
                    String message = ASAPSerialization.readCharSequenceParameter(bis);

                    if (message.startsWith(this.peer.getPeerID().toString())) {
                        String instructions = message.replaceFirst(this.peer.getPeerID().toString(), "");
                        Controller.startCalculations(instructions);
                    }
                } catch (ASAPException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
