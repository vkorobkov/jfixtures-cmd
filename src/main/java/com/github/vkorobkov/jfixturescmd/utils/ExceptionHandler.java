package com.github.vkorobkov.jfixturescmd.utils;

import com.beust.jcommander.ParameterException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class ExceptionHandler {
    private ExceptionHandler() {
    }

    public static void handleException(Exception e) {
        if (e instanceof ParameterException) {
            log.error("Failed to parse command line arguments: " + e.getMessage());
        } else {
            log.error(e.getMessage());
        }
    }
}
