package com.smansu4.thermalmap;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeatMap {
    private static Map<Color, Color> nextIntensityMap;
    private static final int RADIUS = 30;
    private Canvas canvas;
    private static final List<MapColor> heatMapColorsList = List.of(
            new MapColor(255, 255, 255, 1, "white"),
            new MapColor(204, 255, 255, 2, "lightBlue"),
            new MapColor(204, 255, 229, 3, "blue"),
            new MapColor(204, 255, 204, 4, "lightGreen"),
            new MapColor(229, 255, 204, 5, "lightYellow"),
            new MapColor(255, 255, 204, 6, "yellow"),
            new MapColor(255, 229, 204, 7, "orange"),
            new MapColor(255, 204, 204, 8, "red"));


    public HeatMap(Canvas canvas) {
        nextIntensityMap = initializeHeatMap();
        this.canvas = canvas;
    }

    private static Map<Color, Color> initializeHeatMap() {
        Map<Color, Color> map = new HashMap<>();
        for(int i = 0; i < heatMapColorsList.size() - 1; i++) {
            map.put(heatMapColorsList.get(i).getColor(), heatMapColorsList.get(i+1).getColor());
        }

        map.put(heatMapColorsList.getLast().getColor(), heatMapColorsList.getLast().getColor());

        return map;
    }

    public void colorCanvas(int centerX, int centerY) {

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
                if(Utils.coordinateWithinAppBoundary(x, y)) {
                    if (isInsideCircle(x, y, centerX, centerY)) {
                        currentColor = snap.getPixelReader().getColor(x, y);
                        pixelWriter.setColor(x, y, getNextIntensityColor(currentColor));
                    }
                }
            }
        }
    }

    private boolean isInsideCircle(int x, int y, int centerX, int centerY) {
        int distanceX = x - centerX;
        int distanceY = y - centerY;
        return distanceX * distanceX + distanceY * distanceY <= RADIUS * RADIUS;
    }

    private Color getNextIntensityColor(Color currentColor) {
        Color nextIntensityColor = nextIntensityMap.get(currentColor);
        return nextIntensityColor;
    }
}
