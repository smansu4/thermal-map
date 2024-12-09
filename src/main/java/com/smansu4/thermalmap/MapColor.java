package com.smansu4.thermalmap;

import javafx.scene.paint.Color;

public class MapColor {

    private Color color;
    /*
        note: intensity and name is not needed with current implementation of the heat map.
        However, they help with readability and lets the user easily know which color is being
        referred to.
     */
    private String name;
    private int intensity;

    public MapColor(int r, int g, int b, int intensity, String name) {
        this.intensity = intensity;
        this.color = Color.rgb(r, g, b, 0.6);
        this.name = name;
    }

    public Color getColor() {
        return color;
    }
}
