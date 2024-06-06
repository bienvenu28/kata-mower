package com.kata.mower.services;


import com.kata.mower.entities.Bounds;
import com.kata.mower.entities.Instruction;
import com.kata.mower.entities.Mower;
import lombok.Getter;


import java.util.LinkedList;
import java.util.List;

@Getter
public class MowerManager {
    private final List<Mower> mowers = new LinkedList<>();

    public void moveMowers(List<Instruction> instructions, Bounds bounds) {
        for (var instruction : instructions) {
            var mower = new Mower(instruction.initialPosition(), bounds);
            mower.move(instruction.sequence());
            mowers.add(mower);
        }
    }
}
