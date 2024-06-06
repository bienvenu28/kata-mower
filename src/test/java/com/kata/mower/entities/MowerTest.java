package com.kata.mower.entities;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MowerTest {

    @Test
    void moveRight() {
        // Given
        var mower = new Mower(new Position(1, 1, Orientation.E), new Bounds(5, 5));
        var expectedPosition = new Position(2, 1, Orientation.E);
        // When
        mower.moveForward();
        // Then
        assertThat(mower.getPosition()).isEqualTo(expectedPosition);
    }

    @Test
    void moveLeft() {
        // Given
        var mower = new Mower(new Position(1, 1, Orientation.W), new Bounds(5, 5));
        var expectedPosition = new Position(0, 1, Orientation.W);
        // When
        mower.moveForward();
        // Then
        assertThat(mower.getPosition()).isEqualTo(expectedPosition);
    }

    @Test
    void moveDown() {
        // Given
        var mower = new Mower(new Position(1, 1, Orientation.S), new Bounds(5, 5));
        var expectedPosition = new Position(1, 0, Orientation.S);
        // When
        mower.moveForward();
        // Then
        assertThat(mower.getPosition()).isEqualTo(expectedPosition);
    }

    @Test
    void moveUp() {
        // Given
        var mower = new Mower(new Position(1, 1, Orientation.N), new Bounds(5, 5));
        var expectedPosition = new Position(1, 2, Orientation.N);
        // When
        mower.moveForward();
        // Then
        assertThat(mower.getPosition()).isEqualTo(expectedPosition);
    }

    @Test
    void turnLeft() {
        // Given
        var mower = new Mower(new Position(1, 1, Orientation.N), new Bounds(5, 5));
        var expectedPosition = new Position(1, 1, Orientation.W);
        // When
        mower.turnLeft();
        // Then
        assertThat(mower.getPosition()).isEqualTo(expectedPosition);
    }

    @Test
    void turnRight() {
        // Given
        var mower = new Mower(new Position(1, 1, Orientation.N), new Bounds(5, 5));
        var expectedPosition = new Position(1, 1, Orientation.E);
        // When
        mower.turnRight();
        // Then
        assertThat(mower.getPosition()).isEqualTo(expectedPosition);
    }

    @Test
    void doNotMove() {
        // Given
        var mower = new Mower(new Position(1, 1, Orientation.N), new Bounds(1, 1));
        var expectedPosition = new Position(1, 1, Orientation.N);
        // When
        mower.moveForward();
        // Then
        assertThat(mower.getPosition()).isEqualTo(expectedPosition);

    }
}
