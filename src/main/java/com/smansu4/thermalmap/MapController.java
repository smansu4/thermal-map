package com.smansu4.thermalmap;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class MapController implements Initializable {

    @FXML
    Canvas canvas;

    private int currentX;
    private int currentY;
    private HeatMap heatMap;
    private ScreenMap screenMap;

    private AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            if(screenMap.coordinateWithinAppBoundary(currentX, currentY)) {
                screenMap.updateDurationFromProximityToCenter(currentX, currentY);
                heatMap.colorCanvas(currentX, currentY, screenMap);
            }
        }
    };

    @FXML
    protected void setOnMouseMoved(MouseEvent mouseEvent) {
        currentX = (int) mouseEvent.getX();
        currentY = (int) mouseEvent.getY();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        screenMap = new ScreenMap();
        heatMap = new HeatMap(canvas);

        animationTimer.handle(0);
        animationTimer.start();
    }
}