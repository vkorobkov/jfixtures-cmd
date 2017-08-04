package com.github.vkorobkov.jfixturescmd.utils;

import lombok.SneakyThrows;

import java.util.Properties;

public final class PropertiesReader {
    public static final String PROJECT_NAME;
    public static final String PROJECT_VERSION;
    public static final String PROJECT_LINK;
    public static final String J_FIXTURES_VERSION;

    static {
        Properties properties = load();
        PROJECT_NAME = properties.getProperty("jfixtures.cmd.name");
        PROJECT_VERSION = properties.getProperty("jfixtures.cmd.version");
        PROJECT_LINK = properties.getProperty("jfixtures.cmd.link");
        J_FIXTURES_VERSION = properties.getProperty("jfixtures.version");
    }

    private PropertiesReader() {
    }

    @SneakyThrows
    private static Properties load() {
        Properties properties = new Properties();
        properties.load(PropertiesReader.class.getClassLoader().getResourceAsStream("project.properties"));
        return properties;
    }
}
