package com.kata.mower.entities;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.TypedArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class OrientationTest {
    static class toOrientationConverter extends TypedArgumentConverter<String, Orientation> {

        protected toOrientationConverter() {
            super(String.class, Orientation.class);
        }

        @Override
        protected Orientation convert(String s) throws ArgumentConversionException {
            return Orientation.valueOf(s);
        }
    }

    @ParameterizedTest
    @CsvSource({"N, E", "E, S", "S, W", "W, N"})
    void getNewOrientationByTurningRight(@ConvertWith(toOrientationConverter.class) Orientation inputOrientation,
                                         @ConvertWith(toOrientationConverter.class) Orientation expectedOrientation) {
        // When
        var newOrientation = Orientation.getNewOrientation(inputOrientation, Movement.D);
        // Then
        assertThat(newOrientation).isEqualTo(expectedOrientation);
    }

    @ParameterizedTest
    @CsvSource({"N, W", "W, S", "S, E", "E, N"})
    void getNewOrientationByTurningLeft(@ConvertWith(toOrientationConverter.class) Orientation inputOrientation,
                                        @ConvertWith(toOrientationConverter.class) Orientation expectedOrientation) {
        // When
        var newOrientation = Orientation.getNewOrientation(inputOrientation, Movement.G);
        // Then
        assertThat(newOrientation).isEqualTo(expectedOrientation);
    }

}
