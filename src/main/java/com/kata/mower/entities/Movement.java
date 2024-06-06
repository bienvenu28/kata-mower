package com.kata.mower.entities;

import lombok.Getter;

@Getter
public enum Movement {
    A(0, 'A'),
    D(1, 'D'),
    G(-1, 'G');
    private final char letterAsChar;
    private final int value;

    Movement(int value, char letterAsChar) {
        this.value = value;
        this.letterAsChar = letterAsChar;
    }
}
