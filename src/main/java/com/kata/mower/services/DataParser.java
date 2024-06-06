package com.kata.mower.services;

import com.kata.mower.entities.Instruction;
import com.kata.mower.entities.Bounds;

import java.util.List;
import java.util.Optional;

public interface DataParser {
    List<Instruction> parseLines();

    Optional<Bounds> parseBounds();
}
