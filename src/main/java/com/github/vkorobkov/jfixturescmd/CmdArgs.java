package com.github.vkorobkov.jfixturescmd;

import com.github.lalyos.jfiglet.FigletFont;
import com.github.vkorobkov.jfixtures.JFixtures;
import com.github.vkorobkov.jfixtures.fluent.JFixturesResultImpl;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;

import java.lang.reflect.Method;

import static com.github.vkorobkov.jfixturescmd.Prompt.getProperties;

@Slf4j
class CmdArgs {
    private String[] args = null;
    private Options options = new Options();

    CmdArgs(String[] args) {
        this.args = args;
        this.options.addOption("y", "srcYaml", true, "set source YAML folder path");
        this.options.addOption("s", "outputSql", true, "set output SQL file path");
        this.options.addOption("t", "sqlType", true, "set SQL type: " + SqlTypes.getValues().toLowerCase());
        this.options.addOption("h", "help", false, "print help message");
        this.options.addOption("verbose", "be extra verbose");
        this.options.addOption("version", "print the version information and exit");
        this.options.addOption("console", "print result SQL to console");
    }

    @SneakyThrows
    void parse() {
        try {
            if (args.length == 0) {
                help();
            } else {
                CommandLineParser parser = new DefaultParser();
                CommandLine commandLine = parser.parse(options, args);

                if (commandLine.hasOption("h")) {
                    help();
                } else if (commandLine.hasOption("version")) {
                    version();
                } else {
                    parseCmdArguments(commandLine);
                }
            }
        } catch (ParseException e) {
            log.error("Failed to parse command line arguments: " + e.getMessage());
            help();
        }
    }

    @SneakyThrows
    private void parseCmdArguments(final CommandLine commandLine) throws ParseException {
        String srcYaml;
        String sqlType;

        if (commandLine.hasOption("y")) {
            srcYaml = commandLine.getOptionValue("y");
        } else {
            throw new ParseException("Source YAML folder is not set or not valid");
        }

        if (commandLine.hasOption("t") && SqlTypes.contains(commandLine.getOptionValue("t"))) {
            sqlType = commandLine.getOptionValue("t");
        } else {
            throw new ParseException("SQL type is not set or not valid");
        }

        Method method = JFixtures.class.getMethod(sqlType, String.class);
        JFixturesResultImpl result = (JFixturesResultImpl)method.invoke(null, srcYaml);


        if (commandLine.hasOption("console")) {
            log.info(result.asString());
        } else {
            String outputSql = ".";
            if (commandLine.hasOption("s")) {
                outputSql = commandLine.getOptionValue("s");
            }
            result.toFile(outputSql);
        }
    }

    @SneakyThrows
    private void version() {
        log.info(FigletFont.convertOneLine("v" + getProperties().getProperty("version")));
    }

    private void help() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("JFixtures CMD", options);
    }
}
