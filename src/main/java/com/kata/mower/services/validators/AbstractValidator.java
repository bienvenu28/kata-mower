package com.kata.mower.services.validators;

import com.kata.mower.exceptions.InvalidDataFormatException;
import lombok.Setter;

import java.util.List;

@Setter
public abstract class AbstractValidator implements Validatable {
    protected AbstractValidator nextValidator;
    abstract void validate(List<String> lines) throws InvalidDataFormatException;
}
