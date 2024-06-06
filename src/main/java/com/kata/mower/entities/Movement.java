package com.kata.mower.entities;

import lombok.Getter;

@Getter
public enum Movement {
    A('A'),
    D('D'),
    G('G');
    private final char letterAsChar;

    Movement(char letterAsChar) {
        this.letterAsChar = letterAsChar;
    }
}
