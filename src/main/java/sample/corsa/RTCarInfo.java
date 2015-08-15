package sample.corsa;

import sample.StructReader;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Marcos on 8/15/2015.
 */
public class RTCarInfo {

    int identifier;
    int size;

    float speed_Kmh;
    float speed_Mph;
    float speed_Ms;

    boolean isAbsEnabled;
    boolean isAbsInAction;
    boolean isTcInAction;
    boolean isTcEnabled;
    boolean isInPit;
    boolean isEngineLimiterOn;

    float accG_vertical;
    float accG_horizontal;
    float accG_frontal;

    int lapTime;
    int lastLap;
    int bestLap;
    int lapCount;

    float gas;
    float brake;
    float clutch;
    float engineRPM;
    float steer;
    int gear;
    float cgHeight;

    float wheelAngularSpeed[];
    float slipAngle[];
    float slipAngle_ContactPatch[];
    float slipRatio[];
    float tyreSlip[];
    float ndSlip[];
    float load[];
    float Dy[];
    float Mz[];
    float tyreDirtyLevel[];

    float camberRAD[];
    float tyreRadius[];
    float tyreLoadedRadius[];
    float suspensionHeight[];
    float carPositionNormalized;
    float carSlope;
    float carCoordinates[];

    public RTCarInfo(byte[] received) throws IOException {
        StructReader structReader = new StructReader(received);
        identifier = structReader.readInt();
        size = structReader.readInt();
        speed_Kmh = structReader.readFloat();
        speed_Mph = structReader.readFloat();
        speed_Ms = structReader.readFloat();
        isAbsEnabled = structReader.readBool();
        isAbsInAction = structReader.readBool();
        isTcInAction = structReader.readBool();
        isTcEnabled = structReader.readBool();
        isInPit = structReader.readBool();
        isEngineLimiterOn = structReader.readBool();

        accG_vertical = structReader.readFloat();
        accG_horizontal = structReader.readFloat();
        accG_frontal = structReader.readFloat();

        lapTime = structReader.readInt();
        lastLap = structReader.readInt();
        bestLap = structReader.readInt();
        lapCount = structReader.readInt();

        gas = structReader.readFloat();
        brake = structReader.readFloat();
        clutch = structReader.readFloat();
        engineRPM = structReader.readFloat();
        steer = structReader.readFloat();
        gear = structReader.readInt();
        cgHeight = structReader.readFloat();
        wheelAngularSpeed = structReader.readFloats(4);
        slipAngle = structReader.readFloats(4);
        slipAngle_ContactPatch = structReader.readFloats(4);
        slipRatio = structReader.readFloats(4);
        tyreSlip = structReader.readFloats(4);
        ndSlip = structReader.readFloats(4);
        load = structReader.readFloats(4);
        Dy = structReader.readFloats(4);
        Mz = structReader.readFloats(4);
        tyreDirtyLevel = structReader.readFloats(4);

        camberRAD = structReader.readFloats(4);
        tyreRadius = structReader.readFloats(4);
        tyreLoadedRadius = structReader.readFloats(4);
        suspensionHeight = structReader.readFloats(4);
        carPositionNormalized = structReader.readFloat();
        carSlope = structReader.readFloat();

        carCoordinates = structReader.readFloats(3);
    }

    public int getIdentifier() {
        return identifier;
    }

    public int getSize() {
        return size;
    }

    public float getSpeed_Kmh() {
        return speed_Kmh;
    }

    public float getSpeed_Mph() {
        return speed_Mph;
    }

    public float getSpeed_Ms() {
        return speed_Ms;
    }

    public boolean isAbsEnabled() {
        return isAbsEnabled;
    }

    public boolean isAbsInAction() {
        return isAbsInAction;
    }

    public boolean isTcInAction() {
        return isTcInAction;
    }

    public boolean isTcEnabled() {
        return isTcEnabled;
    }

    public boolean isInPit() {
        return isInPit;
    }

    public boolean isEngineLimiterOn() {
        return isEngineLimiterOn;
    }

    public float getAccG_vertical() {
        return accG_vertical;
    }

    public float getAccG_horizontal() {
        return accG_horizontal;
    }

    public float getAccG_frontal() {
        return accG_frontal;
    }

    public int getLapTime() {
        return lapTime;
    }

    public int getLastLap() {
        return lastLap;
    }

    public int getBestLap() {
        return bestLap;
    }

    public int getLapCount() {
        return lapCount;
    }

    public float getGas() {
        return gas;
    }

    public float getBrake() {
        return brake;
    }

    public float getClutch() {
        return clutch;
    }

    public float getEngineRPM() {
        return engineRPM;
    }

    @Override
    public String toString() {
        return "RTCarInfo{" +
                "identifier=" + identifier +
                ", size=" + size +
                ", speed_Kmh=" + speed_Kmh +
                ", speed_Mph=" + speed_Mph +
                ", speed_Ms=" + speed_Ms +
                ", isAbsEnabled=" + isAbsEnabled +
                ", isAbsInAction=" + isAbsInAction +
                ", isTcInAction=" + isTcInAction +
                ", isTcEnabled=" + isTcEnabled +
                ", isInPit=" + isInPit +
                ", isEngineLimiterOn=" + isEngineLimiterOn +
                ", accG_vertical=" + accG_vertical +
                ", accG_horizontal=" + accG_horizontal +
                ", accG_frontal=" + accG_frontal +
                ", lapTime=" + lapTime +
                ", lastLap=" + lastLap +
                ", bestLap=" + bestLap +
                ", lapCount=" + lapCount +
                ", gas=" + gas +
                ", brake=" + brake +
                ", clutch=" + clutch +
                ", engineRPM=" + engineRPM +
                ", steer=" + steer +
                ", gear=" + gear +
                ", cgHeight=" + cgHeight +
                ", wheelAngularSpeed=" + Arrays.toString(wheelAngularSpeed) +
                ", slipAngle=" + Arrays.toString(slipAngle) +
                ", slipAngle_ContactPatch=" + Arrays.toString(slipAngle_ContactPatch) +
                ", slipRatio=" + Arrays.toString(slipRatio) +
                ", tyreSlip=" + Arrays.toString(tyreSlip) +
                ", ndSlip=" + Arrays.toString(ndSlip) +
                ", load=" + Arrays.toString(load) +
                ", Dy=" + Arrays.toString(Dy) +
                ", Mz=" + Arrays.toString(Mz) +
                ", tyreDirtyLevel=" + Arrays.toString(tyreDirtyLevel) +
                ", camberRAD=" + Arrays.toString(camberRAD) +
                ", tyreRadius=" + Arrays.toString(tyreRadius) +
                ", tyreLoadedRadius=" + Arrays.toString(tyreLoadedRadius) +
                ", suspensionHeight=" + Arrays.toString(suspensionHeight) +
                ", carPositionNormalized=" + carPositionNormalized +
                ", carSlope=" + carSlope +
                ", carCoordinates=" + Arrays.toString(carCoordinates) +
                '}';
    }
}
