package com.github.vkorobkov.jfixturescmd.cmd;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.github.vkorobkov.jfixtures.JFixtures;
import com.github.vkorobkov.jfixtures.fluent.JFixturesResult;
import com.github.vkorobkov.jfixturescmd.utils.PropertiesReader;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class CmdParser {
    private final CmdArgs cmdArgs = new CmdArgs();

    public void parse(String[] args) {
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

    private void generateSql() {
        String destination = cmdArgs.getDestination();
        JFixturesResult result = JFixtures.byDialect(cmdArgs.getSource(), cmdArgs.getSqlType());
        result.toFile(destination);
        log.info("The SQL file has bees successfully created: " + destination);
    }
}
