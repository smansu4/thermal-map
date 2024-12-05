package com.smansu4.thermalmap;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class MapController {

    @FXML
    Canvas canvas;

    @FXML
    protected void onMouseOver() {
        canvas.setOnMouseMoved(mouseEvent -> {
            int x = (int) mouseEvent.getX();
            int y = (int) mouseEvent.getY();

            PixelWriter pixelWriter = canvas.getGraphicsContext2D().getPixelWriter();
            pixelWriter.setColor(x, y, Color.ORCHID);
        });
    }
}