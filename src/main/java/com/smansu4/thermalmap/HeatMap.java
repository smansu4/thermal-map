package com.smansu4.thermalmap;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

import java.util.List;

public class HeatMap {
    static final int MAX_HEAT = 350;
    private Canvas canvas;
    private static final List<MapColor> heatMapColorsList = List.of(
            new MapColor(204, 255, 255, 2, "lightBlue"),
            new MapColor(204, 255, 229, 3, "blue"),
            new MapColor(204, 255, 204, 4, "lightGreen"),
            new MapColor(229, 255, 204, 5, "lightYellow"),
            new MapColor(255, 255, 204, 6, "yellow"),
            new MapColor(255, 229, 204, 7, "orange"),
            new MapColor(255, 204, 204, 8, "red"));

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
        if(duration < 50) {
            return heatMapColorsList.get(0).getColor();
        } else if(duration < 100) {
            return heatMapColorsList.get(1).getColor();
        } else if(duration < 150) {
            return heatMapColorsList.get(2).getColor();
        } else if(duration < 200) {
            return heatMapColorsList.get(3).getColor();
        } else if(duration < 250) {
            return heatMapColorsList.get(4).getColor();
        } else if(duration < 300) {
            return heatMapColorsList.get(5).getColor();
        } else if(duration < MAX_HEAT) {
            return heatMapColorsList.get(6).getColor();
        }

        return heatMapColorsList.getLast().getColor();
    }
}
