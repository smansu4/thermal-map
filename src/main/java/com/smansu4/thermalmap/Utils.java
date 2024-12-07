package com.smansu4.thermalmap;

public class Utils {
    public static boolean coordinateWithinAppBoundary(int x, int y) {
        return x > 0 && x < ThermalMapApplication.APP_WIDTH
                && y > 0 && y < ThermalMapApplication.APP_HEIGHT;
    }
}
