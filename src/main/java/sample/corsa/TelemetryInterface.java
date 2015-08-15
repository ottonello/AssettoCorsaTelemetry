package sample.corsa;

import sample.StructWriter;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

public class TelemetryInterface {
    DatagramSocket clientSocket;
    public static final int CORSA_PORT = 9996;
    private Status status = Status.init;
    InetAddress IPAddress;

    public void connect() {
        try {
            startHandshake();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startHandshake() throws IOException {
        IPAddress = InetAddress.getByName("localhost");

        clientSocket = new DatagramSocket(9999);
        clientSocket.connect(IPAddress, CORSA_PORT);

        byte[] receiveData = new byte[1024];

        byte[] handShake = getHandshake();
        clientSocket.send(new DatagramPacket(handShake, handShake.length));

        DatagramPacket receivePacket = new DatagramPacket(receiveData, HandshakeResponse.SIZE);
        clientSocket.receive(receivePacket);

        HandshakeResponse handshakeResponse = new HandshakeResponse(receiveData);
        System.out.println("Handshake: " + handshakeResponse);

        status = Status.handshake;

        byte[] subscribe = getSubscribeUpdates();
        clientSocket.send(new DatagramPacket(subscribe, subscribe.length));

        System.out.println("Subscribed");
        status = Status.subscribed;

        while (!Status.dismissed.equals(status)) {
            System.out.println("Receiving");

            DatagramPacket receivedLapData = new DatagramPacket(receiveData, 328);
            clientSocket.receive(receivedLapData);

            RTCarInfo telemetry = new RTCarInfo(receiveData);
            System.out.println("RECEIVED: " + telemetry);
        }
        System.out.println("Bye");

    }

    private byte[] getHandshake() throws IOException {
        StructWriter structWriter = new StructWriter(12);
        structWriter.writeInt(1);
        structWriter.writeInt(1);
        structWriter.writeInt(OperationId.HANDSHAKE);
        return structWriter.toByteArray();
    }

    private byte[] getSubscribeUpdates() throws IOException {
        StructWriter structWriter = new StructWriter(12);
        structWriter.writeInt(1);
        structWriter.writeInt(1);
        structWriter.writeInt(OperationId.SUBSCRIBE_UPDATE);
        return structWriter.toByteArray();
    }

    private byte[] getSubscribeSpot() throws IOException {
        StructWriter structWriter = new StructWriter(12);
        structWriter.writeInt(1);
        structWriter.writeInt(1);
        structWriter.writeInt(OperationId.SUBSCRIBE_SPOT);
        return structWriter.toByteArray();
    }

    private byte[] getDismiss() throws IOException {
        StructWriter structWriter = new StructWriter(12);
        structWriter.writeInt(1);
        structWriter.writeInt(1);
        structWriter.writeInt(OperationId.DISMISS);
        return structWriter.toByteArray();    }

    public void stop() throws IOException {
        System.out.println("Dismissing");

        byte[] dismiss = getDismiss();

        clientSocket.send(new DatagramPacket(dismiss, dismiss.length, IPAddress, CORSA_PORT));

        clientSocket.close();
        status = Status.dismissed;

    }
}
