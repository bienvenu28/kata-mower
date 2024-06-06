package com.kata.mower.services;

import com.kata.mower.exceptions.InvalidFileException;

import java.util.List;

public interface DataReader {
    List<String> readLines() throws InvalidFileException;
}
