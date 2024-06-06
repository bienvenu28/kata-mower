package com.kata.mower.services.validators;

import com.kata.mower.exceptions.InvalidDataFormatException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Order(2)
public class PositionValidator extends AbstractValidator {
    @Override
    public boolean isValid(String s) {
        return s.matches("^\\d \\d [N|E|S|W]$");
    }

    @Override
    void validate(List<String> lines) throws InvalidDataFormatException {
        for (var i = 1; i < lines.size(); i += 2) {
            if (!isValid(lines.get(i)))
                throw new InvalidDataFormatException("Invalid position: " + lines.get(i));
        }
        if (Objects.nonNull(super.nextValidator))
            super.nextValidator.validate(lines);
    }
}
