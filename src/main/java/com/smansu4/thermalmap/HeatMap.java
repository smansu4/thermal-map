package com.smansu4.thermalmap;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

import java.util.List;

public class HeatMap {
    static final int MAX_HEAT = 350;
    private static final int HEAT_DIFFERENCE_CONSTANT = 50;
    private Canvas canvas;
    //note: intensity and color name are not needed anymore. The MapColor class can be deleted,
    //  and the type replaced with the Color class type. However, keeping them makes
    //  updating the colors easier.
    private static final List<MapColor> heatMapColorsList = List.of(
            new MapColor(153, 255, 255, 1, "light blue"),
            new MapColor(153, 255, 229, 2, "sea foam green"),
            new MapColor(153, 255, 153, 3, "light green"),
            new MapColor(229, 255, 153, 4, "yellow green"),
            new MapColor(255, 255, 153, 5, "yellow"),
            new MapColor(255, 229, 153, 6, "orange"),
            new MapColor(255, 153, 153, 7, "red"));

    public HeatMap(Canvas canvas) {
        this.canvas = canvas;
    }

    public void colorCanvas(int centerX, int centerY, ScreenMap screenMap) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        PixelWriter pixelWriter = gc.getPixelWriter();

        int xInitialPos = centerX - ScreenMap.RADIUS;
        int xEndPos = centerX + ScreenMap.RADIUS;
        int yInitialPos = centerY - ScreenMap.RADIUS;
        int yEndPos = centerY + ScreenMap.RADIUS;

        for (int x = xInitialPos; x <= xEndPos; x++) {
            for (int y = yInitialPos; y <= yEndPos; y++) {
                if(screenMap.coordinateWithinAppBoundary(x, y)) {
                    if (screenMap.isInsideCircle(x, y, centerX, centerY)) {
                        pixelWriter.setColor(x, y, getColorFromDuration(screenMap.getMap()[x][y]));
                    }
                }
            }
        }
    }

    //ignore decimals for mapping
    private Color getColorFromDuration(double duration) {
        if(duration < HEAT_DIFFERENCE_CONSTANT) {
            return heatMapColorsList.get(0).getColor();
        } else if(duration < HEAT_DIFFERENCE_CONSTANT * 2) {
            return heatMapColorsList.get(1).getColor();
        } else if(duration < HEAT_DIFFERENCE_CONSTANT * 3) {
            return heatMapColorsList.get(2).getColor();
        } else if(duration < HEAT_DIFFERENCE_CONSTANT * 4) {
            return heatMapColorsList.get(3).getColor();
        } else if(duration < HEAT_DIFFERENCE_CONSTANT * 5) {
            return heatMapColorsList.get(4).getColor();
        } else if(duration < HEAT_DIFFERENCE_CONSTANT * 6) {
            return heatMapColorsList.get(5).getColor();
        } else if(duration < MAX_HEAT) {
            return heatMapColorsList.get(6).getColor();
        }

        return heatMapColorsList.getLast().getColor();
    }
}
