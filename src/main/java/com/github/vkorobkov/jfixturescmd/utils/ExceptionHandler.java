package com.github.vkorobkov.jfixturescmd.utils;

import com.beust.jcommander.ParameterException;
import com.github.vkorobkov.jfixtures.loader.LoaderException;
import com.github.vkorobkov.jfixtures.processor.ProcessorException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class ExceptionHandler {
    private ExceptionHandler() {
    }

    public static void handleException(Exception e) {
        if (e instanceof ParameterException) {
            log.error("Failed to parse command line arguments: " + e.getMessage());
        } else if (e instanceof LoaderException) {
            log.error("Failed to load fixtures: " + e.getMessage());
        } else if (e instanceof ProcessorException) {
            log.error("Failed to process fixtures: " + e.getMessage());
        } else {
            log.error(e.getMessage(), e);
        }
    }
}
