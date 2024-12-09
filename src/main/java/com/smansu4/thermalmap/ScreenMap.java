package com.smansu4.thermalmap;

public class ScreenMap {
    public static final int RADIUS = 20;
    private final double[][] map;

    public ScreenMap() {
        this.map = new double[ThermalMapApplication.APP_WIDTH][ThermalMapApplication.APP_HEIGHT];
    }

    public boolean coordinateWithinAppBoundary(int x, int y) {
        return x > 0 && x < ThermalMapApplication.APP_WIDTH
                && y > 0 && y < ThermalMapApplication.APP_HEIGHT;
    }

    //a^2 + b^2 = c^2
    boolean isInsideCircle(int x, int y, int centerX, int centerY) {
        int distanceX = x - centerX;
        int distanceY = y - centerY;
        return distanceX * distanceX + distanceY * distanceY <= RADIUS * RADIUS;
    }

    public int distanceFromCenter(int x, int y, int centerX, int centerY) {
        int distanceX = x - centerX;
        int distanceY = y - centerY;
        return distanceX * distanceX + distanceY * distanceY;
    }

    public void updateHoverTimeOnPixel(int x, int y, int centerX, int centerY) {
        if(map[x][y] >= HeatMap.MAX_HEAT) return;

        double timeUnit = 1;
        int distFromCenter = distanceFromCenter(x, y, centerX, centerY);

        if(distFromCenter <= (RADIUS*RADIUS/2)) map[x][y] += timeUnit;
        else if (distFromCenter <= (RADIUS*RADIUS/1.5)) map[x][y] += timeUnit/2;
        else map[x][y] += timeUnit/4;
    }

    public void updateDurationFromProximityToCenter(int centerX, int centerY) {
        int xInitialPos = centerX - RADIUS;
        int xEndPos = centerX + RADIUS;
        int yInitialPos = centerY - RADIUS;
        int yEndPos = centerY + RADIUS;

        for (int x = xInitialPos; x <= xEndPos; x++) {
            for (int y = yInitialPos; y <= yEndPos; y++) {
                if(coordinateWithinAppBoundary(x, y)) {
                    if (isInsideCircle(x, y, centerX, centerY)) {
                        updateHoverTimeOnPixel(x,y,centerX, centerY);
                    }
                }
            }
        }
    }

    public double[][] getMap() {
        return map;
    }
}
