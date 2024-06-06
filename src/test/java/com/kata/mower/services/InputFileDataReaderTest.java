package com.kata.mower.services;

import com.kata.mower.exceptions.InvalidFileException;
import com.kata.mower.services.formatters.LineFormatter;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


class InputFileDataReaderTest {

    @Test
    void readLines_ShouldReturnAListOfString_WhenProvidingAnExistingFilePath() throws InvalidFileException {
        // Given
        var inputFilePath = "src/test/resources/input.txt";
        var formatter = new LineFormatter();
        var inputFileDataReader = new InputFileDataReader(inputFilePath, formatter);
        // When
        var lines = inputFileDataReader.readLines();
        // Then
        assertThat(lines)
                .asList()
                .hasSize(5)
                .contains("5 5", "1 2 N", "GAGAGAGAA", "3 3 E", "AADAADADDA");
    }

    @Test
    void readLines_ShouldThrowAndInvalidFileException_WhenProvidingAFilePathWhichDoesNotExist() {
        // Given
        String inputFilePath = "src/test/resources/doNotExit.txt";
        var formatter = new LineFormatter();
        var inputFileDataReader = new InputFileDataReader(inputFilePath, formatter);
        // Then
        assertThatThrownBy(inputFileDataReader::readLines)
                .isInstanceOf(InvalidFileException.class)
                .hasMessageStartingWith("Invalid file:");
    }
    @Test
    void readLines_ShouldReturnAnEmptyListOfStrings_WhenProvidingAnEmptyFile() throws InvalidFileException {
        // Given
        var inputFilePath = "src/test/resources/empty.txt";
        var formatter = new LineFormatter();
        var inputFileDataReader = new InputFileDataReader(inputFilePath, formatter);
        // When
        var lines = inputFileDataReader.readLines();
        // Then
        assertThat(lines)
                .asList()
                .isEmpty();
    }

}
