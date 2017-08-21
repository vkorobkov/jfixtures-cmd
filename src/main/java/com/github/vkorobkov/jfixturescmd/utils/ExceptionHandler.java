package com.github.vkorobkov.jfixturescmd.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class ExceptionHandler {
    private ExceptionHandler() {
    }

    public static void handleException(Exception exception) {
        log.error(exception.getMessage());
    }
}
