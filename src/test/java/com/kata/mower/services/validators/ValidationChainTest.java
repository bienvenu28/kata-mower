package com.kata.mower.services.validators;

import com.kata.mower.exceptions.InvalidDataFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class ValidationChainTest {

    private ValidationChain chain;

    @BeforeEach
    void setUp() {
        chain = new ValidationChain(new ArrayList<>());
        chain.addValidator(new BoundsValidator());
        chain.addValidator(new PositionValidator());
        chain.addValidator(new SequenceValidator());
    }

    @Test
    void validate_ShouldBeNotThrowAnyException() {
        // Given
        var lines = List.of("5 5", "1 2 N", "GAGAGAGAA", "3 3 E", "AADAADADDA");
        // Then
        assertDoesNotThrow(() -> chain.validate(lines));
    }

    @Test
    void validate_ShouldThrowAndInvalidDataFormatException_ForBoundsValidation() {
        // Given
        var lines = List.of("5 *", "1 2 N", "GAGAGAGAA", "3 3 E", "AADAADADDA");
        // Then
        assertThatThrownBy(() -> chain.validate(lines))
                .isInstanceOf(InvalidDataFormatException.class)
                .hasMessageStartingWith("Invalid bounds");
    }

    @Test
    void validate_ShouldThrowAndInvalidDataFormatException_ForPositionValidation() {
        // Given
        var lines = List.of("5 5", "1 2 Z", "GAGAGAGAA", "3 3 E", "AADAADADDA");
        // Then
        assertThatThrownBy(() -> chain.validate(lines))
                .isInstanceOf(InvalidDataFormatException.class)
                .hasMessageStartingWith("Invalid position");
    }

    @Test
    void validate_ShouldThrowAndInvalidDataFormatException_ForSequenceValidation() {
        // Given
        var lines = List.of("5 5", "1 2 N", "XAGAGAGAA", "3 3 E", "AADAADADDA");
        // Then
        assertThatThrownBy(() -> chain.validate(lines))
                .isInstanceOf(InvalidDataFormatException.class)
                .hasMessageStartingWith("Invalid sequence");
    }

}
