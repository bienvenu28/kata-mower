package com.kata.mower;

import com.kata.mower.exceptions.InvalidDataFormatException;
import com.kata.mower.exceptions.InvalidFileException;
import com.kata.mower.services.formatters.LineFormatter;
import com.kata.mower.services.InputFileDataParser;
import com.kata.mower.services.InputFileDataReader;
import com.kata.mower.services.MowerManager;
import com.kata.mower.services.validators.BoundsValidator;
import com.kata.mower.services.validators.PositionValidator;
import com.kata.mower.services.validators.SequenceValidator;
import com.kata.mower.services.validators.ValidationChain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FileProcessorRunner implements CommandLineRunner {
    @Value("${input.file.path}")
    private String inputFilePath;
    @Override
    public void run(String... args) {
        try {
            log.info("Start processing file {}", inputFilePath);
            log.info("############ Reading data ############");
            var formatter = new LineFormatter();
            var dataReader = new InputFileDataReader(inputFilePath, formatter);
            var lines = dataReader.readLines();
            log.info("############ Validating lines ############");
            var validationChain = new ValidationChain();
            validationChain.addValidator(new BoundsValidator());
            validationChain.addValidator(new PositionValidator());
            validationChain.addValidator(new SequenceValidator());
            validationChain.validate(lines);
            log.info("############ Parsing lines ############");
            var dataParser = new InputFileDataParser(lines);
            var instructions = dataParser.parseLines();
            var bounds = dataParser.parseBounds().orElseThrow();
            log.info("############ Moving mowers ############");
            var mowerManager = new MowerManager();
            mowerManager.moveMowers(instructions, bounds);
            log.info("############ Printing mowers position ############");
            mowerManager.getMowers().forEach(mower -> log.info(mower.toString()));
        } catch (InvalidFileException e) {
            log.error("An error occurs {}", e.getMessage());
        } catch (InvalidDataFormatException e) {
            log.error(e.getMessage());
        }
    }
}
