package com.github.vkorobkov.jfixturescmd;

import com.github.lalyos.jfiglet.FigletFont;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
final class Prompt {
    private Prompt() {
    }

    @SneakyThrows
    static void write() {
        final String asciiJFixtures = FigletFont.convertOneLine("JFIXTURES");
        log.info(asciiJFixtures);
        log.info("For more info please visit https://github.com/vkorobkov/jfixtures-cmd");
    }
}
