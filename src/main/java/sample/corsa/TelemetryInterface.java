package sample.corsa;

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

        ByteBuffer handShake = getHandshake();
        clientSocket.send(new DatagramPacket(handShake.array(), handShake.capacity()));

        DatagramPacket receivePacket = new DatagramPacket(receiveData, HandshakeResponse.SIZE);
        clientSocket.receive(receivePacket);

        HandshakeResponse handshakeResponse = new HandshakeResponse(receiveData);
        System.out.println("Handshake: " + handshakeResponse);

        status = Status.handshake;

        ByteBuffer subscribe = getSubscribeUpdates();
        clientSocket.send(new DatagramPacket(subscribe.array(), subscribe.capacity()));

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

    private ByteBuffer getHandshake() {
        ByteBuffer sendData = ByteBuffer.allocate(4 * 3);
        sendData.putInt(1);// identifier
        sendData.putInt(1);// version
        sendData.putInt(OperationId.HANDSHAKE);// operation
        return sendData;
    }

    private ByteBuffer getSubscribeUpdates() {
        ByteBuffer sendData = ByteBuffer.allocate(4 * 3);
        sendData.putInt(1);// identifier
        sendData.putInt(1);// version
        sendData.putInt(OperationId.SUBSCRIBE_UPDATE);// operation
        return sendData;
    }

    private ByteBuffer getSubscribeSpot() {
        ByteBuffer sendData = ByteBuffer.allocate(4 * 3);
        sendData.putInt(1);// identifier
        sendData.putInt(1);// version
        sendData.putInt(OperationId.SUBSCRIBE_SPOT);// operation
        return sendData;
    }

    private ByteBuffer getDismiss() {
        ByteBuffer sendData = ByteBuffer.allocate(4 * 3);
        sendData.putInt(1);// identifier
        sendData.putInt(1);// version
        sendData.putInt(OperationId.DISMISS);// operation
        return sendData;
    }

    public void stop() throws IOException {
        System.out.println("Dismissing");

        ByteBuffer dismiss = getDismiss();

        clientSocket.send(new DatagramPacket(dismiss.array(), dismiss.capacity(), IPAddress, CORSA_PORT));

        clientSocket.close();
        status = Status.dismissed;

    }
}
