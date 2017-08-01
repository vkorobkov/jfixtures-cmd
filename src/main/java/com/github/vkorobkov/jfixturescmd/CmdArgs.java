package com.github.vkorobkov.jfixturescmd;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.github.vkorobkov.jfixtures.JFixtures;
import com.github.vkorobkov.jfixtures.fluent.JFixturesResultImpl;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

@Slf4j
final class CmdArgs {
    @Parameter(names = "-type", description = "SQL type", required = true, converter = SqlTypesConverter.class, order = 0)
    private SqlTypes sqlType;
    @Parameter(names = "-src", description = "Source YAML folder path", required = true, order = 1)
    private String srcYaml;
    @Parameter(names = "-target", description = "Target SQL file path", required = true, order = 2)
    private String outputSql;
    @Parameter(names = {"-c", "--console"}, description = "Print result SQL to console", order = 3)
    private boolean console;
    @Parameter(names = {"-h", "--help"}, help = true, order = 4)
    private boolean help;

    void parse(final String[] args) {
        final JCommander jCommander = JCommander.newBuilder()
                .addObject(this)
                .programName("JFixtures CMD")
                .build();

        try {
            jCommander.parse(args);

            if (args.length == 0 || this.help) {
                jCommander.usage();
            } else {
                parseCmdArguments();
            }
        } catch (ParameterException e) {
            log.error("Failed to parse command line arguments: " + e.getMessage());
            jCommander.usage();
        }
    }

    @SneakyThrows
    private void parseCmdArguments() {
        Method method = JFixtures.class.getMethod(String.valueOf(sqlType).toLowerCase(), String.class);
        JFixturesResultImpl result = (JFixturesResultImpl)method.invoke(null, srcYaml);

        if (console) {
            log.info(result.asString());
        } else {
            result.toFile(outputSql);
        }
    }
}
