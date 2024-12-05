package com.smansu4.thermalmap;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class MapController {

    @FXML
    Canvas canvas;

    private static final int RADIUS = 10;

    @FXML
    protected void onMouseOver() {
        canvas.setOnMouseMoved(mouseEvent -> {
            int centerX = (int) mouseEvent.getX();
            int centerY = (int) mouseEvent.getY();

            PixelWriter pixelWriter = canvas.getGraphicsContext2D().getPixelWriter();

            int xInitialPos = centerX - RADIUS;
            int xEndPos = centerX + RADIUS;
            int yInitialPos = centerY - RADIUS;
            int yEndPos = centerY + RADIUS;

            for (int x = xInitialPos; x <= xEndPos; x++) {
                for (int y = yInitialPos; y <= yEndPos; y++) {
                    if (isInsideCircle(x, y, centerX, centerY)) {
                        pixelWriter.setColor(x, y, Color.ORCHID);
                    }
                }
            }
        });
    }

    private static boolean isInsideCircle(int x, int y, int centerX, int centerY) {
        int distanceX = x - centerX;
        int distanceY = y - centerY;
        return distanceX * distanceX + distanceY * distanceY <= RADIUS * RADIUS;
    }
}