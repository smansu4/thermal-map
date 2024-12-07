package com.smansu4.thermalmap;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class MapController implements Initializable {

    @FXML
    Canvas canvas;

    private final int DURATION_SECONDS = 15;
    private int currentX;
    private int currentY;
    private LocalDateTime lastMovementTimestamp;
    private HeatMap heatMap;

    private AnimationTimer animationTimer = new AnimationTimer() {

        @Override
        public void handle(long l) {
            if(Utils.coordinateWithinAppBoundary(currentX, currentY)) {
                LocalDateTime now = LocalDateTime.now(Clock.systemUTC());

                if(now.minusSeconds(DURATION_SECONDS).isBefore(lastMovementTimestamp)) {
                    heatMap.colorCanvas(currentX, currentY);
                }
            }
        }
    };

    @FXML
    protected void setOnMouseMoved(MouseEvent mouseEvent) {
        lastMovementTimestamp = LocalDateTime.now(Clock.systemUTC());

        currentX = (int) mouseEvent.getX();
        currentY = (int) mouseEvent.getY();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        heatMap = new HeatMap(canvas);

        lastMovementTimestamp = LocalDateTime.now(Clock.systemUTC());

        animationTimer.handle(0);
        animationTimer.start();
    }
}