package com.smansu4.thermalmap;

public class Utils {
    static final int RADIUS = 20;
    static final int MAX_HEAT = 350;

    static boolean coordinateWithinAppBoundary(int x, int y) {
        return x > 0 && x < ThermalMapApplication.APP_WIDTH
                && y > 0 && y < ThermalMapApplication.APP_HEIGHT;
    }

    //a^2 + b^2 = c^2
    static boolean isInsideCircle(int x, int y, int centerX, int centerY) {
        int distanceX = x - centerX;
        int distanceY = y - centerY;
        return distanceX * distanceX + distanceY * distanceY <= RADIUS * RADIUS;
    }

    static int distanceFromCenter(int x, int y, int centerX, int centerY) {
        int distanceX = x - centerX;
        int distanceY = y - centerY;
        return distanceX * distanceX + distanceY * distanceY;
    }

    private static void updateHoverTimeOnPixel(int x, int y, int centerX, int centerY, double[][] screenMap) {
        if(screenMap[x][y] >= MAX_HEAT) return;

        double timeUnit = 1;
        int distFromCenter = distanceFromCenter(x, y, centerX, centerY);

        if(distFromCenter <= (RADIUS*RADIUS/2)) screenMap[x][y] += timeUnit;
        else if (distFromCenter <= (RADIUS*RADIUS/1.5)) screenMap[x][y] += timeUnit/2;
        else screenMap[x][y] += timeUnit/4;
    }

    static void updateDurationFromProximityToCenter(int centerX, int centerY, double[][] screenMap) {
        int xInitialPos = centerX - Utils.RADIUS;
        int xEndPos = centerX + Utils.RADIUS;
        int yInitialPos = centerY - Utils.RADIUS;
        int yEndPos = centerY + Utils.RADIUS;

        for (int x = xInitialPos; x <= xEndPos; x++) {
            for (int y = yInitialPos; y <= yEndPos; y++) {
                if(Utils.coordinateWithinAppBoundary(x, y)) {
                    if (Utils.isInsideCircle(x, y, centerX, centerY)) {
                        updateHoverTimeOnPixel(x,y,centerX, centerY, screenMap);
                    }
                }
            }
        }
    }
}
