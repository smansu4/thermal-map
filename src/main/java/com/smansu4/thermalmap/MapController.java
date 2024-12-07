package com.smansu4.thermalmap;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

import static com.smansu4.thermalmap.ThermalMapApplication.APP_HEIGHT;
import static com.smansu4.thermalmap.ThermalMapApplication.APP_WIDTH;
import static com.smansu4.thermalmap.Utils.updateDurationFromProximityToCenter;

public class MapController implements Initializable {

    @FXML
    Canvas canvas;

    private int currentX;
    private int currentY;
    private HeatMap heatMap;
    private double[][] screenMap = new double[APP_WIDTH][APP_HEIGHT];

    private AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            if(Utils.coordinateWithinAppBoundary(currentX, currentY)) {
                updateDurationFromProximityToCenter(currentX, currentY, screenMap);
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
        heatMap = new HeatMap(canvas);

        animationTimer.handle(0);
        animationTimer.start();
    }
}