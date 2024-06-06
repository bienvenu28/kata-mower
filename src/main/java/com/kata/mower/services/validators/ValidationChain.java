package com.kata.mower.services.validators;

import com.kata.mower.exceptions.InvalidDataFormatException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;

@Service
public class ValidationChain {

    private final List<AbstractValidator> validators;
    @Autowired
    public ValidationChain(List<AbstractValidator> validators) {
        this.validators = validators;
    }

    public void addValidator(AbstractValidator validator) {
        if (!validators.isEmpty()) {
            var lastValidator = validators.get(validators.size() - 1);
            lastValidator.setNextValidator(validator);
        }
        validators.add(validator);
    }

    @PostConstruct
    public void chainValidators() {
        if (validators.isEmpty())
            return;
        for (int i = 1; i < validators.size() - 1; i++) {
            var previousValidator = validators.get(i - 1);
            previousValidator.setNextValidator(validators.get(i));
        }
    }

    public void validate(List<String> lines) throws InvalidDataFormatException {
        if (Objects.isNull(lines) || lines.isEmpty())
            throw new InvalidDataFormatException("Invalid data format no lines to validate");
        for (var validator : validators) {
            validator.validate(lines);
        }
    }
}
