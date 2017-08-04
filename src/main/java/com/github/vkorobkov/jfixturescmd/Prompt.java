package com.github.vkorobkov.jfixturescmd;

import com.github.lalyos.jfiglet.FigletFont;
import com.github.vkorobkov.jfixturescmd.utils.PropertiesReader;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;


@Slf4j
final class Prompt {
    private Prompt() {
    }

    @SneakyThrows
    static void write() {
        String asciiJFixtures = FigletFont.convertOneLine("JFIXTURES");
        log.info(asciiJFixtures);
        log.info("JFixtures CMD version: " + PropertiesReader.PROJECT_VERSION);
        log.info("JFixtures version: " + PropertiesReader.J_FIXTURES_VERSION);
        log.info(PropertiesReader.PROJECT_LINK);
    }
}
