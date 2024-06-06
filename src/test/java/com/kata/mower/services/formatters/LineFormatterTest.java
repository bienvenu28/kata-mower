package com.kata.mower.services.formatters;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LineFormatterTest {

    @ParameterizedTest
    @CsvSource({" 5 5, 5 5", "5   5, 5 5", " A   B , A B"})
    void format(String input, String expected) {
        // Given
        var formatter = new LineFormatter();
        //When
        var result = formatter.format(input);
        assertThat(result).isEqualTo(expected);
    }
}
