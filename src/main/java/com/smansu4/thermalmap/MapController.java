package com.smansu4.thermalmap;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

public class MapController implements Initializable {

    @FXML
    Canvas canvas;

    @FXML
    Pane pane;

    private static final int RADIUS = 20;
    private int currentX;
    private int currentY;
    private LocalDateTime lastMovementTimestamp;

    List<MapColor> heatMapColors = List.of(
            new MapColor(204, 255, 204, 1, "lightGreen"),
            new MapColor(229, 255, 204, 2, "lightYellow"),
            new MapColor(255, 255, 204, 3, "yellow"),
            new MapColor(255, 229, 204, 4, "orange"),
            new MapColor(255, 204, 204, 5, "red"));

    private AnimationTimer animationTimer = new AnimationTimer() {

        @Override
        public void handle(long l) {
            LocalDateTime now = LocalDateTime.now(Clock.systemUTC());

            if(now.minusSeconds(5).isBefore(lastMovementTimestamp)) {
                colorCanvas(currentX, currentY, heatMapColors.get(0));
            }
            else if(now.minusSeconds(10).isBefore(lastMovementTimestamp)) {
                colorCanvas(currentX, currentY, heatMapColors.get(1));
            }
            else if(now.minusSeconds(15).isBefore(lastMovementTimestamp)) {
                colorCanvas(currentX, currentY, heatMapColors.get(2));
            }
            else if(now.minusSeconds(20).isBefore(lastMovementTimestamp)) {
                colorCanvas(currentX, currentY, heatMapColors.get(4));
            }
            else if(now.minusSeconds(20).isBefore(lastMovementTimestamp)) {
                colorCanvas(currentX, currentY, heatMapColors.get(5));
            }
        }
    };

    @FXML
    protected void setOnMouseMoved(MouseEvent mouseEvent) {
        lastMovementTimestamp = LocalDateTime.now(Clock.systemUTC());

        int x = (int) mouseEvent.getX();
        int y = (int) mouseEvent.getY();
        currentX = x;
        currentY = y;

    }

    private boolean isInsideCircle(int x, int y, int centerX, int centerY) {
        int distanceX = x - centerX;
        int distanceY = y - centerY;
        return distanceX * distanceX + distanceY * distanceY <= RADIUS * RADIUS;
    }

    private void colorCanvas(int centerX, int centerY, MapColor color) {

        GraphicsContext gc = canvas.getGraphicsContext2D();
        PixelWriter pixelWriter = gc.getPixelWriter();
        WritableImage snap = gc.getCanvas().snapshot(null, null);

        Color currentColor;
        int xInitialPos = centerX - RADIUS;
        int xEndPos = centerX + RADIUS;
        int yInitialPos = centerY - RADIUS;
        int yEndPos = centerY + RADIUS;

        for (int x = xInitialPos; x <= xEndPos; x++) {
            for (int y = yInitialPos; y <= yEndPos; y++) {
                if(x >=0 && y >= 0)
                {
                    if (isInsideCircle(x, y, centerX, centerY)) {
                        currentColor = snap.getPixelReader().getColor(x, y);
                        int intensity = getCorrelatedColorIntensity(currentColor);

                        if (intensity < color.getIntensity())
                            pixelWriter.setColor(x, y, color.getColor());
                    }
                }
            }
        }
    }

    private int getCorrelatedColorIntensity(Color currentColor) {
        for(MapColor c : heatMapColors) {
            if(c.getColor().equals(currentColor)) {
                return c.getIntensity();
            }
        }
        return 0;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lastMovementTimestamp = LocalDateTime.now(Clock.systemUTC());
        animationTimer.handle(0);
        animationTimer.start();
    }
}