package com.kata.mower.services;


import com.kata.mower.entities.Instruction;
import com.kata.mower.entities.Orientation;
import com.kata.mower.entities.Position;
import com.kata.mower.entities.Bounds;
import lombok.AllArgsConstructor;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
public class InputFileDataParser implements DataParser {
    private final List<String> lines;
    private final String SPACE_SEPARATOR = " ";

    @Override
    public Optional<Bounds> parseBounds() {
        if (lines.isEmpty()) {
            return Optional.empty();
        }
        var firstLine = lines.get(0);
        var XY = firstLine.split(SPACE_SEPARATOR);
        return Optional.of(new Bounds(Integer.parseInt(XY[0]), Integer.parseInt(XY[1])));
    }

    private Position parsePosition(String positionAsString) {
        var arr = positionAsString.split(SPACE_SEPARATOR);
        var x = Integer.parseInt(arr[0]);
        var y = Integer.parseInt(arr[1]);
        var orientation = Orientation.valueOf(arr[2]);
        return new Position(x, y, orientation);
    }

    @Override
    public List<Instruction> parseLines() {
        if (lines.isEmpty())
            return List.of();
        var startIndex = 1;
        var remainingLines = lines.subList(startIndex, lines.size());
        var iterator = remainingLines.iterator();
        var instructions = new ArrayList<Instruction>();
        while (iterator.hasNext()) {
            var position = parsePosition(iterator.next());
            var sequence = iterator.next();
            instructions.add(new Instruction(position, sequence));
        }
        return instructions;
    }
}
