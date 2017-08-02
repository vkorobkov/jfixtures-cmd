package com.github.vkorobkov.jfixturescmd;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.github.vkorobkov.jfixtures.JFixtures;
import com.github.vkorobkov.jfixtures.fluent.JFixturesResultImpl;
import com.github.vkorobkov.jfixturescmd.utils.PropertiesReader;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

@Slf4j
final class CmdParser {
    private final CmdArgs cmdArgs = new CmdArgs();

    void parse(final String[] args) {
        final JCommander jCommander = JCommander.newBuilder()
                .addObject(cmdArgs)
                .programName(PropertiesReader.PROJECT_NAME)
                .build();

        try {
            jCommander.parse(args);

            if (args.length == 0 || cmdArgs.isHelp()) {
                jCommander.usage();
            } else {
                generateSql();
            }
        } catch (ParameterException e) {
            log.error("Failed to parse command line arguments: " + e.getMessage());
            jCommander.usage();
        }
    }

    @SneakyThrows
    private void generateSql() {
        Method method = JFixtures.class.getMethod(String.valueOf(cmdArgs.getSqlType()).toLowerCase(), String.class);
        JFixturesResultImpl result = (JFixturesResultImpl)method.invoke(null, cmdArgs.getSource());
        result.toFile(cmdArgs.getDestination());
    }
}
