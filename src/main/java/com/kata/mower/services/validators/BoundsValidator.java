package com.kata.mower.services.validators;

import com.kata.mower.exceptions.InvalidDataFormatException;
import lombok.Setter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Setter
@Service
@Order(1)
public class BoundsValidator extends AbstractValidator {

    @Override
    public boolean isValid(String s) {
        var boundsMatcher = "^\\d \\d$";
        return s.matches(boundsMatcher);
    }

    public void validate(List<String> lines) throws InvalidDataFormatException {
        if (!isValid(lines.get(0)))
            throw new InvalidDataFormatException("Invalid bounds " + lines.get(0));
        if (Objects.nonNull(super.nextValidator))
            nextValidator.validate(lines);
    }
}
