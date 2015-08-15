package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sample.corsa.TelemetryInterface;

import java.io.IOException;

public class Controller {
    TelemetryService telemetryService;

    public Controller() {
        telemetryService = new TelemetryService();
    }

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        new Thread(new Runnable() {
            public void run() {
                telemetryService.start();
            }
        }).start();
    }

    public void handleStopButtonAction(ActionEvent actionEvent) {
        new Thread(new Runnable() {
            public void run() {
                telemetryService.stop();
            }
        }).start();
    }
}
