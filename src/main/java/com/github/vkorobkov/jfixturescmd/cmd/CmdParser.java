package com.github.vkorobkov.jfixturescmd.cmd;

import com.beust.jcommander.JCommander;
import com.github.vkorobkov.jfixtures.JFixtures;
import com.github.vkorobkov.jfixtures.fluent.JFixturesResult;
import com.github.vkorobkov.jfixtures.sql.SqlType;
import com.github.vkorobkov.jfixturescmd.utils.ExceptionHandler;
import com.github.vkorobkov.jfixturescmd.utils.PropertiesReader;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class CmdParser {
    private final CmdArgs cmdArgs = new CmdArgs();

    public void parse(String[] args) {
        final JCommander jCommander = buildJCommander();

        try {
            log.debug("Start parsing command line...");
            jCommander.parse(args);

            if (cmdArgs.isHelp()) {
                jCommander.usage();
            } else {
                generateSql();
            }
        } catch (Exception e) {
            ExceptionHandler.handleException(e);
            jCommander.usage();
        }
    }

    private JCommander buildJCommander() {
        return JCommander.newBuilder()
                .addObject(cmdArgs)
                .programName(PropertiesReader.PROJECT_NAME)
                .build();
    }

    private void generateSql() {
        String destination = cmdArgs.getDestination();
        String fixturesFolder = cmdArgs.getSource();
        SqlType sqlType = cmdArgs.getSqlType();

        log.info("Fixtures folder: " + fixturesFolder);
        log.info("SQL type: " + sqlType);
        JFixturesResult result = JFixtures.byDialect(fixturesFolder, sqlType);
        result.toFile(destination);
        log.info("\nSUCCESS (destination file: " + destination + ")\n");
    }
}
