package sample.corsa;

import sample.StructReader;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Marcos on 8/15/2015.
 */
public class RTLap {
    int carIdentifierNumber;
    int lap;
    String driverName;
    String carName;
    int time;

    public RTLap(byte[] received) throws IOException {
        StructReader structReader = new StructReader(received);
        carIdentifierNumber = structReader.readInt();
        lap = structReader.readInt();
        driverName = structReader.readChars(50);
        carName = structReader.readChars(50);
        time = structReader.readInt();
    }

    public int getCarIdentifierNumber() {
        return carIdentifierNumber;
    }

    public int getLap() {
        return lap;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getCarName() {
        return carName;
    }

    public int getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "RTLap{" +
                "carIdentifierNumber=" + carIdentifierNumber +
                ", lap=" + lap +
                ", driverName='" + driverName + '\'' +
                ", carName='" + carName + '\'' +
                ", time=" + time +
                '}';
    }
}
