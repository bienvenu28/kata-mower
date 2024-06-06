package com.kata.mower.services.formatters;

import org.springframework.stereotype.Service;

@Service
public class LineFormatter implements Formatter {
    @Override
    public String format(String s) {
        var MULTI_SPACE_PATTERN = "\\s+";
        var SINGLE_SPACE_PATTERN = " ";
        return s.strip().replaceAll(MULTI_SPACE_PATTERN, SINGLE_SPACE_PATTERN);
    }
}
