package com.smansu4.thermalmap;

import javafx.scene.paint.Color;

public class MapColor {

    private Color color;
    private String name;
    private int intensity;

    public MapColor(int r, int g, int b, int intensity, String name) {
        this.intensity = intensity;
        this.color = Color.rgb(r, g, b);
        this.name = name;
        
    }

    public Color getColor() {
        return color;
    }

    public int getIntensity() {
        return intensity;
    }

    public String getName() {
        return name;
    }
}
