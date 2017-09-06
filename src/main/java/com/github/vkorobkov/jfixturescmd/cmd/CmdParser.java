package com.github.vkorobkov.jfixturescmd.cmd;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.github.vkorobkov.jfixtures.JFixtures;
import com.github.vkorobkov.jfixtures.sql.SqlType;
import com.github.vkorobkov.jfixturescmd.utils.EnumUtil;
import com.github.vkorobkov.jfixturescmd.utils.ExceptionHandler;
import com.github.vkorobkov.jfixturescmd.utils.PropertiesReader;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

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

    @SneakyThrows
    private void generateData() {
        val destination = cmdArgs.getDestination();
        val fixturesFolder = cmdArgs.getSource();
        val type = cmdArgs.getDestinationType();

        log.info("Fixtures folder: " + fixturesFolder);
        if (Objects.equals(type.toLowerCase(), "xml")) {
            log.info("Export type: XML");
            JFixtures.xml(fixturesFolder).toFile(destination);
        } else {
            log.info("SQL type: " + type);
            JFixtures.byDialect(fixturesFolder, convertToSqlType(type)).toFile(destination);
        }
        log.info("\nSUCCESS (destination file: " + destination + ")\n");
    }

    @SneakyThrows
    private SqlType convertToSqlType(final String value) {
        Optional<SqlType> sqlType = EnumUtil.valueOf(SqlType.class, value.toUpperCase());
        return sqlType.orElseThrow(() -> {
                    String availableTypes = Arrays.toString(SqlType.values()).toLowerCase();
                    throw new ParameterException("SQL type '" + value + "' is not valid. "
                            + "Available SQL types are: " + availableTypes);
                }
        );
    }

    private JCommander buildJCommander() {
        return JCommander.newBuilder()
                .addObject(cmdArgs)
                .programName(PropertiesReader.PROJECT_NAME)
                .build();
    }
}
