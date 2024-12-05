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

    private AnimationTimer animationTimer = new AnimationTimer() {

        @Override
        public void handle(long l) {
            LocalDateTime now = LocalDateTime.now(Clock.systemUTC());

            if(now.minusSeconds(5).isBefore(lastMovementTimestamp)) {
                colorCanvas(currentX, currentY, Color.rgb(204, 255, 204));
            }
            else if(now.minusSeconds(10).isBefore(lastMovementTimestamp)) {
                colorCanvas(currentX, currentY, Color.rgb(229, 255, 204));
            }
            else if(now.minusSeconds(15).isBefore(lastMovementTimestamp)) {
                colorCanvas(currentX, currentY, Color.rgb(255, 255, 204));
            }
            else if(now.minusSeconds(20).isBefore(lastMovementTimestamp)) {
                colorCanvas(currentX, currentY, Color.rgb(255, 229, 204));
            }
            else if(now.minusSeconds(20).isBefore(lastMovementTimestamp)) {
                colorCanvas(currentX, currentY, Color.rgb(255, 204, 204));
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

    private void colorCanvas(int centerX, int centerY, Color color) {

        GraphicsContext gc = canvas.getGraphicsContext2D();
        PixelWriter pixelWriter = gc.getPixelWriter();
        WritableImage snap = gc.getCanvas().snapshot(null, null);
        Color currentColor = snap.getPixelReader().getColor(centerX, centerY);

        int xInitialPos = centerX - RADIUS;
        int xEndPos = centerX + RADIUS;
        int yInitialPos = centerY - RADIUS;
        int yEndPos = centerY + RADIUS;

        for (int x = xInitialPos; x <= xEndPos; x++) {
            for (int y = yInitialPos; y <= yEndPos; y++) {
                if (isInsideCircle(x, y, centerX, centerY)) {
                    pixelWriter.setColor(x, y, color);
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lastMovementTimestamp = LocalDateTime.now(Clock.systemUTC());
        animationTimer.handle(0);
        animationTimer.start();
    }
}