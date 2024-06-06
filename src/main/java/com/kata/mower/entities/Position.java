package com.kata.mower.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class Position {
    private int x;
    private int y;
    private Orientation orientation;
    private final int offset = 1;

    public int getXIncremented() {
        return x + offset;
    }

    public int getXDecremented() {
        return x - offset;
    }

    public int getYDecremented() {
        return y - offset;
    }

    public int getYIncremented() {
        return y + offset;
    }

    @Override
    public String toString() {
        return String.format("%d %d %s", x, y, orientation);
    }
}
