package com.github.vkorobkov.jfixturescmd;

import com.github.lalyos.jfiglet.FigletFont;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

@Slf4j
final class Prompt {
    private static final Properties properties = new Properties();

    private Prompt() {
    }

    @SneakyThrows
    static void write() {
        final String asciiJFixtures = FigletFont.convertOneLine("JFIXTURES");
        log.info(asciiJFixtures);
        log.info("JFixtures CMD version: " + getProperties().getProperty("version"));
        log.info("JFixtures version: " + getProperties().getProperty("jfixtures.version"));
        log.info("For more info please visit https://github.com/vkorobkov/jfixtures-cmd");
    }

    @SneakyThrows
    static Properties getProperties() {
        properties.load(Prompt.class.getClassLoader().getResourceAsStream("project.properties"));
        return properties;
    }
}
