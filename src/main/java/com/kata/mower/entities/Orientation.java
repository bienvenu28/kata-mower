package com.kata.mower.entities;

public enum Orientation {
    N,
    E,
    W,
    S;
    public static Orientation getNewOrientation(Orientation currentOrientation, Movement movement) {
        if (movement.equals(Movement.D))
            return switch (currentOrientation) {
              case N -> E;
              case E -> S;
              case S -> W;
              case W -> N;
            };
        return switch (currentOrientation) {
            case N -> W;
            case W -> S;
            case S -> E;
            case E -> N;
        };
    }
}
