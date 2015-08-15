package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sample.corsa.TelemetryInterface;

import java.io.IOException;

public class Controller {
    TelemetryInterface telemetryInterface;

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        new Thread(new Runnable() {
            public void run() {
                telemetryInterface = new TelemetryInterface();
                telemetryInterface.connect();
            }
        }).start();
    }

    public void handleStopButtonAction(ActionEvent actionEvent) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    telemetryInterface.stop();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
