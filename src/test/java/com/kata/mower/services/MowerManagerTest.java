package com.kata.mower.services;

import com.kata.mower.entities.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class MowerManagerTest {
    @Test
    void testMowerManager() {
        // Given
        var instructions = List.of(
                new Instruction(new Position(1, 2, Orientation.N), "GAGAGAGAA"),
                new Instruction(new Position(3, 3, Orientation.E), "AADAADADDA")
        );
        var bounds = new Bounds(5, 5);
        var mowerManager = new MowerManager();
        var expectedResult = List.of(
                new Mower(new Position(1, 3, Orientation.N), bounds),
                new Mower(new Position(5, 1, Orientation.E), bounds)
        );
        // When
        mowerManager.moveMowers(instructions, bounds);
        // Then
        assertThat(mowerManager.getMowers()).isEqualTo(expectedResult);

    }

}
