package com.github.vkorobkov.jfixturescmd.cmd;

import com.beust.jcommander.JCommander;
import com.github.vkorobkov.jfixtures.JFixtures;
import com.github.vkorobkov.jfixturescmd.utils.ExceptionHandler;
import com.github.vkorobkov.jfixturescmd.utils.PropertiesReader;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Slf4j
public final class CmdParser {
    private final CmdArgs cmdArgs = new CmdArgs();

    public void parse(String[] args) {
        val jCommander = buildJCommander();

        try {
            log.debug("Start parsing command line...");
            jCommander.parse(args);

            if (cmdArgs.isHelp()) {
                jCommander.usage();
            } else {
                generateData();
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

    private void generateData() {
        val destination = cmdArgs.getDestination();
        val fixturesFolder = cmdArgs.getSource();
        val sqlType = cmdArgs.getSqlType();

        log.info("Fixtures folder: " + fixturesFolder);
        if (cmdArgs.isXml()) {
            log.info("Export type: XML");
            JFixtures.xml(fixturesFolder).toFile(destination);
        } else {
            log.info("SQL type: " + sqlType);
            JFixtures.byDialect(fixturesFolder, sqlType).toFile(destination);
        }
        log.info("\nSUCCESS (destination file: " + destination + ")\n");
    }
}
