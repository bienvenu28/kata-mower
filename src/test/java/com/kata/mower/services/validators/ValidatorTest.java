package com.kata.mower.services.validators;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"5 5", "1 1", "0 0"})
    void validBounds_ShouldReturnTrue_WhenBoundsIsValid(String boundsAsString) {
        // Given
        var validator = new BoundsValidator();
        // When
        var isValid = validator.isValid(boundsAsString);
        // Then
        assertThat(isValid).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "5   5", " 5", "A", "A 5"})
    void validBounds_ShouldReturnFalse_WhenBoundsIsInValid(String boundsAsString) {
        // Given
        var validator = new BoundsValidator();
        // When
        var isValid = validator.isValid(boundsAsString);
        // Then
        assertThat(isValid).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"3 3 N", "0 0 E"})
    void validPosition_ShouldReturnTrue_WhenPositionIsValid(String positionAsString) {
        // Given
        var validator = new PositionValidator();
        // When
        var isValid = validator.isValid(positionAsString);
        // Then
        assertThat(isValid).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "1 2", "N 2 1", "1 2 Z", "N 4 5", "4 N 3"})
    void validPosition_ShouldReturnFalse_WhenPositionIsInValid(String positionAsString) {
        // Given
        var validator = new PositionValidator();
        // When
        var isValid = validator.isValid(positionAsString);
        // Then
        assertThat(isValid).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"A", "AAAA", "GDDGGDA", "G", "D", "A"})
    void validSequence_ShouldReturnTrue_WhenSequenceIsValid(String sequenceAsString) {
        // Given
        var validator = new SequenceValidator();
        // When
        var isValid = validator.isValid(sequenceAsString);
        // Then
        assertThat(isValid).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "ZE", "RZRE", "adg", "dgdg", "gdA"})
    void validSequence_ShouldReturnFalse_WhenSequenceIsInValid(String sequenceAsString) {
        // Given
        var validator = new SequenceValidator();
        // When
        var isValid = validator.isValid(sequenceAsString);
        // Then
        assertThat(isValid).isFalse();
    }
}
