package com.kata.mower;

import com.kata.mower.exceptions.InvalidDataFormatException;
import com.kata.mower.exceptions.InvalidFileException;
import com.kata.mower.services.*;
import com.kata.mower.services.validators.ValidationChain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FileProcessorRunner implements CommandLineRunner {

    private final DataReader inputFileDataReader;
    private final ValidationChain validationChain;
    private final DataParser inputFileDataParser;
    private final MowerManager mowerManager;

    public FileProcessorRunner(
            InputFileDataReader inputFileDataReader,
            ValidationChain validationChain,
            InputFileDataParser inputFileDataParser,
            MowerManager mowerManager
    ) {
        this.inputFileDataReader = inputFileDataReader;
        this.validationChain = validationChain;
        this.inputFileDataParser = inputFileDataParser;
        this.mowerManager = mowerManager;
    }

    @Override
    public void run(String... args) {
        try {
            log.info("############ STEP 1 Read data ############");
            var lines = inputFileDataReader.readLines();
            log.info("############ STEP 2 Validate data ############");
            validationChain.validate(lines);
            log.info("############ STEP 3 Parse data ############");
            ((InputFileDataParser) inputFileDataParser).setLines(lines);
            var instructions = inputFileDataParser.parseLines();
            var bounds = inputFileDataParser.parseBounds().orElseThrow();
            log.info("############ STEP 4 Process data ############");
            mowerManager.moveMowers(instructions, bounds);
            log.info("############ STEP 5 Print mower's position ############");
            mowerManager.getMowers().forEach(mower -> log.info(mower.toString()));

        } catch (InvalidFileException e) {
            log.error("An error occurs {}", e.getMessage());
        } catch (InvalidDataFormatException e) {
            log.error(e.getMessage());
        }
    }
}
