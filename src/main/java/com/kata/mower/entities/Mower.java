package com.kata.mower.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class Mower {
    private Position position;
    private final int offset = 1;
    private Bounds bounds;

    public void move(String instructions) {
        for (var i = 0; i < instructions.length(); i++) {
            var c = instructions.charAt(i);
            if (c == Movement.G.getLetterAsChar())
                turnLeft();
            if (c == Movement.D.getLetterAsChar())
                turnRight();
            if (c == Movement.A.getLetterAsChar())
                moveForward();

        }
    }

    public void turnLeft() {
        position.setOrientation(Orientation.getNewOrientation(position.getOrientation(), Movement.G));
    }

    public void turnRight() {
        position.setOrientation(Orientation.getNewOrientation(position.getOrientation(), Movement.D));

    }

    public void moveForward() {
        switch (position.getOrientation()) {
            case N:
                if (bounds.isValidY(position.getYIncremented()))
                    position.setY(position.getYIncremented());
                break;
            case S:
                if (bounds.isValidY(position.getYDecremented()))
                    position.setY(position.getYDecremented());
                break;
            case E:
                if (bounds.isValidX(position.getXIncremented()))
                    position.setX(position.getXIncremented());
                break;
            case W:
                if (bounds.isValidX(position.getXDecremented()))
                    position.setX(position.getXDecremented());
                break;
        }
    }

    @Override
    public String toString() {
        return position.toString();
    }
}
