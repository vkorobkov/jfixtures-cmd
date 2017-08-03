package com.github.vkorobkov.jfixturescmd;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.github.vkorobkov.jfixtures.JFixtures;
import com.github.vkorobkov.jfixtures.fluent.JFixturesResult;
import com.github.vkorobkov.jfixtures.fluent.JFixturesResultImpl;
import com.github.vkorobkov.jfixturescmd.utils.PropertiesReader;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
final class CmdParser {
    private CmdArgs cmdArgs = new CmdArgs();

    void parse(String[] args) {
        final JCommander jCommander = buildJCommander();

        try {
            jCommander.parse(args);

            if (cmdArgs.isHelp()) {
                jCommander.usage();
            } else {
                generateSql();
            }
        } catch (ParameterException e) {
            log.error("Failed to parse command line arguments: " + e.getMessage());
            jCommander.usage();
        }
    }

    private JCommander buildJCommander() {
        return JCommander.newBuilder()
                .addObject(cmdArgs)
                .programName(PropertiesReader.PROJECT_NAME)
                .build();
    }

    @SneakyThrows
    private void generateSql() {
        JFixturesResultImpl result = (JFixturesResultImpl)chooseDialect(cmdArgs.getSqlType(), cmdArgs.getSource());
        result.toFile(cmdArgs.getDestination());
    }

    private JFixturesResult chooseDialect(SqlType type, String src) {
        switch (type) {
        case POSTGRES:
            return JFixtures.postgres(src);
        case MYSQL:
            return JFixtures.mysql(src);
        case H2:
            return JFixtures.h2(src);
        case CLICKHOUSE:
            return JFixtures.clickHouse(src);
        default:
            throw new IllegalStateException(type + " is not supported.");
        }
    }
}
