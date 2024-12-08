package com.smansu4.thermalmap;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.smansu4.thermalmap.Utils.MAX_HEAT;

public class HeatMap {
    private static Map<Color, Color> nextIntensityMap;
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
        nextIntensityMap = initializeHeatColorMap();
        this.canvas = canvas;
    }

    private static Map<Color, Color> initializeHeatColorMap() {
        Map<Color, Color> map = new HashMap<>();
        for(int i = 0; i < heatMapColorsList.size() - 1; i++) {
            map.put(heatMapColorsList.get(i).getColor(), heatMapColorsList.get(i+1).getColor());
        }

        map.put(heatMapColorsList.getLast().getColor(), heatMapColorsList.getLast().getColor());

        return map;
    }

    public void colorCanvas(int centerX, int centerY, double[][] screenMap) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        PixelWriter pixelWriter = gc.getPixelWriter();

        int xInitialPos = centerX - Utils.RADIUS;
        int xEndPos = centerX + Utils.RADIUS;
        int yInitialPos = centerY - Utils.RADIUS;
        int yEndPos = centerY + Utils.RADIUS;

        for (int x = xInitialPos; x <= xEndPos; x++) {
            for (int y = yInitialPos; y <= yEndPos; y++) {
                if(Utils.coordinateWithinAppBoundary(x, y)) {
                    if (Utils.isInsideCircle(x, y, centerX, centerY)) {
                        pixelWriter.setColor(x, y, getColorFromDuration(screenMap[x][y]));
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
