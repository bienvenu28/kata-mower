package com.kata.mower.services;

import com.kata.mower.entities.Bounds;
import com.kata.mower.entities.Instruction;
import com.kata.mower.entities.Orientation;
import com.kata.mower.entities.Position;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class InputFileDataParserTest {

    @Test
    void parseLines_ShouldReturnAListOfInstructions_WhenProvidingAListOfStrings() {
        // Given
        var lines = List.of("5 5", "1 2 N", "GAGAGAGAA", "3 3 E", "AADAADADDA");
        var inputFileDataParser = new InputFileDataParser(lines);
        var expectedInstructions = List.of(
                new Instruction(new Position(1, 2, Orientation.N), "GAGAGAGAA"),
                new Instruction(new Position(3, 3, Orientation.E), "AADAADADDA")
        );
        // When
        var instructions = inputFileDataParser.parseLines();
        // Then
        assertThat(instructions).asList().hasSize(2)
                .isEqualTo(expectedInstructions);
    }

    @Test
    void parseLines_ShouldReturnAnEmptyListOfInstructions_WhenProvidingAnEmptyListOfStrings() {
        // Given
        var inputFileDataParser = new InputFileDataParser(List.of());
        var expectedInstructions = List.of();
        // When
        var instructions = inputFileDataParser.parseLines();
        // Then
        assertThat(instructions).asList().hasSize(0)
                .isEqualTo(expectedInstructions);
    }

    @Test
    void parseBounds_ShouldReturnAnOptionalOfBound_WhenProvidingListOfStrings() {
        // Given
        var lines = List.of("5 5", "1 2 N", "GAGAGAGAA", "3 3 E", "AADAADADDA");
        var inputFileDataParser = new InputFileDataParser(lines);
        var expectedOptionalBound = Optional.of(new Bounds(5, 5));
        // When
        var bounds = inputFileDataParser.parseBounds();
        // Then
        assertThat(bounds).isNotEmpty()
                .isEqualTo(expectedOptionalBound);
    }

    @Test
    void parseBounds_ShouldReturnAnEmptyOptionalOfBound_WhenProvidingAnEmptyListOfStrings() {
        // Given
        var inputFileDataParser = new InputFileDataParser(List.of());
        var expectedOptionalBound = Optional.empty();
        // When
        var bounds = inputFileDataParser.parseBounds();
        // Then
        assertThat(bounds).isEmpty()
                .isEqualTo(expectedOptionalBound);
    }

}
