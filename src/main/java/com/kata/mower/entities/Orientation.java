package com.kata.mower.entities;

public enum Orientation {
    N(0),
    E(1),
    S(2),
    W(3);
    private final int value;
    Orientation(int value) {
        this.value = value;
    }
    public static Orientation getNewOrientation(Orientation currentOrientation, Movement movement) {
        var orientationsArray = Orientation.values();
        var newOrientationIndex = Math.floorMod(currentOrientation.value + movement.getValue(), orientationsArray.length);
        return orientationsArray[newOrientationIndex];
    }
}
