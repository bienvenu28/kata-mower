package com.kata.mower.services.validators;

import com.kata.mower.exceptions.InvalidDataFormatException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ValidationChain {
    private final List<AbstractValidator> validators = new ArrayList<>();

    public void addValidator(AbstractValidator validator) {
        if (!validators.isEmpty()) {
            var lastValidator = validators.get(validators.size() - 1);
            lastValidator.setNextValidator(validator);
        }
        validators.add(validator);
    }

    public void validate(List<String> lines) throws InvalidDataFormatException {
        if (Objects.isNull(lines) || lines.isEmpty())
            throw new InvalidDataFormatException("Invalid data format no lines to validate");
        for (var validator : validators) {
            validator.validate(lines);
        }
    }
}
