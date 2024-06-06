package com.kata.mower.services.validators;

import com.kata.mower.exceptions.InvalidDataFormatException;

import java.util.List;
import java.util.Objects;

public class SequenceValidator extends AbstractValidator {

    @Override
    public boolean isValid(String s) {
        var sequenceMatcher = "^[A|G|D]+$";
        return s.matches(sequenceMatcher);
    }

    @Override
    void validate(List<String> lines) throws InvalidDataFormatException {
        for (var i = 2; i < lines.size(); i += 2) {
            if (!isValid(lines.get(i)))
                throw new InvalidDataFormatException("Invalid sequence " + lines.get(i));
        }
        if (Objects.nonNull(super.nextValidator))
            super.nextValidator.validate(lines);
    }
}
