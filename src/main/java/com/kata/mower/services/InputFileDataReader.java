package com.kata.mower.services;

import com.kata.mower.exceptions.InvalidFileException;
import com.kata.mower.services.formatters.Formatter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@AllArgsConstructor
public class InputFileDataReader implements DataReader {
    private final String fileName;
    private final Formatter lineFormatter;

    @Override
    public List<String> readLines() throws InvalidFileException {
        var path = Paths.get(fileName);
        try (var stream = Files.lines(path)) {
            return stream.map(lineFormatter::format)
                    .toList();
        } catch (NoSuchFileException e) {
            throw new InvalidFileException("Invalid file: no file found at path " + e.getMessage());
        } catch (IOException e) {
            throw new InvalidFileException("Invalid file: error when reading file at path " + e.getMessage());
        }
    }
}
