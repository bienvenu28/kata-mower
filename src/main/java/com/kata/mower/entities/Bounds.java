package com.kata.mower.entities;


public record Bounds(int upperX, int upperY) {

    public boolean isValidY(int y) {
        return y >= 0 && y <= upperY;
    }
    public boolean isValidX(int x) {
        return x >= 0 && x <= upperX;
    }
}
