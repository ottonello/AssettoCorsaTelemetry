package sample.corsa;

import sample.StructReader;

import java.io.IOException;

public class HandshakeResponse {
    public static final int CAR_NAME_SIZE = 50;
    public static final int DRIVER_NAME_SIZE = 50;
    public static final int TRACK_NAME_SIZE = 50;
    public static final int TRACK_CONFIG_SIZE = 50;
    public static final int SIZE =
            CAR_NAME_SIZE *2 +
                    DRIVER_NAME_SIZE * 2 +
                    TRACK_NAME_SIZE * 2 +
                    TRACK_CONFIG_SIZE *2 +
                    4 +
                    4;

    String driverName;
    String carName;
    String trackName;
    String trackConfig;
    int identifier;
    int version;

    public HandshakeResponse(byte[] received) throws IOException {
        StructReader structReader = new StructReader(received);

        carName = structReader.readChars(CAR_NAME_SIZE);
        driverName =structReader.readChars(DRIVER_NAME_SIZE);
        identifier = structReader.readInt();
        version = structReader.readInt();
        trackName = structReader.readChars(TRACK_NAME_SIZE);
        trackConfig = structReader.readChars(TRACK_CONFIG_SIZE);
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getTrackConfig() {
        return trackConfig;
    }

    public void setTrackConfig(String trackConfig) {
        this.trackConfig = trackConfig;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "HandshakeResponse{" +
                "carName='" + carName + '\'' +
                ", driverName='" + driverName + '\'' +
                ", identifier=" + identifier +
                ", version=" + version +
                ", trackName='" + trackName + '\'' +
                ", trackConfig='" + trackConfig + '\'' +
                '}';
    }
}
