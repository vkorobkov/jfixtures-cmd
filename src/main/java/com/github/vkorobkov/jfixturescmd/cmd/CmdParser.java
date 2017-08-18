package com.github.vkorobkov.jfixturescmd.cmd;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.github.vkorobkov.jfixtures.JFixtures;
import com.github.vkorobkov.jfixtures.fluent.JFixturesResult;
import com.github.vkorobkov.jfixtures.sql.SqlType;
import com.github.vkorobkov.jfixturescmd.utils.PropertiesReader;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

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

    private void generateSql() {
        String destination = cmdArgs.getDestination();
        String fixturesFolder = cmdArgs.getSource();
        SqlType sqlType = cmdArgs.getSqlType();

        if (!new File(fixturesFolder).isDirectory()) {
            throw new ParameterException("Source folder with fixtures '" + fixturesFolder + "' does not exist");
        }

        log.info("Fixtures folder: " + fixturesFolder);
        log.info("SQL type: " + sqlType);
        JFixturesResult result = JFixtures.byDialect(fixturesFolder, sqlType);
        result.toFile(destination);
        log.info("\nSUCCESS (destination file: " + destination + ")\n");
    }
}
