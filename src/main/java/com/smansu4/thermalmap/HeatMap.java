package com.smansu4.thermalmap;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

import java.util.List;

public class HeatMap {
    //note: intensity and color name are not needed anymore. The MapColor class can be deleted,
    //  and the type replaced with the Color class type. However, keeping them makes
    //  updating the colors easier.
    private static final List<MapColor> heatMapColorsList = List.of(
            new MapColor(0, 0, 255, 1, "dark blue"),
            new MapColor(0, 128, 225, 2, "light blue"),
            new MapColor(0, 255, 225, 3, "sea foam green"),
            new MapColor(0, 255, 128, 4, "light green"),
            new MapColor(0, 255, 0, 5, "green"),
            new MapColor(128, 255, 0, 6, "yellow green"),
            new MapColor(255, 225, 0, 7, "yellow"),
            new MapColor(255, 128, 0, 8, "orange"),
            new MapColor(255, 0, 0, 9, "red")
    );
    private static final int HEAT_DIFFERENCE_CONSTANT = 50;
    public static final int MAX_HEAT = HEAT_DIFFERENCE_CONSTANT * heatMapColorsList.size();
    private Canvas canvas;

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
        } else if(duration < HEAT_DIFFERENCE_CONSTANT * 7) {
            return heatMapColorsList.get(6).getColor();
        } else if(duration < HEAT_DIFFERENCE_CONSTANT * 8) {
            return heatMapColorsList.get(7).getColor();
        }

        return heatMapColorsList.get(8).getColor();
    }
}
