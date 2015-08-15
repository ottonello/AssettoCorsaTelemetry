package sample;

import sample.arduino.ArduinoSerial;
import sample.corsa.RTCarInfo;
import sample.corsa.TelemetryInterface;

import java.io.IOException;

/**
 * Created by Marcos on 8/15/2015.
 */
public class TelemetryService {
    TelemetryInterface telemetryInterface;
    ArduinoSerial arduinoSerial;
    boolean stop =false;

    public TelemetryService() {
        telemetryInterface = new TelemetryInterface();
        arduinoSerial = new ArduinoSerial();
    }

    public void start(){
        telemetryInterface = new TelemetryInterface();
        telemetryInterface.connect();
        arduinoSerial.initialize();

        new Thread(new Runnable() {
            public void run() {
                while(!stop){
                    RTCarInfo telemetry = telemetryInterface.getTelemetry();
                    if(telemetry !=null){
                        float speed_kmh = telemetry.getSpeed_Kmh();
//                        System.out.println(telemetry);
                        arduinoSerial.writeInt((int) speed_kmh);
                    }
                    try {
                        Thread.sleep(15);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void stop(){
        try {
            stop = true;
            telemetryInterface.stop();
            arduinoSerial.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
