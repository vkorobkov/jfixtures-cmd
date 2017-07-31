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
        CommandLineParser parser = new DefaultParser();
        CommandLine commandLine;
        try {
            commandLine = parser.parse(options, args);

            if (args.length == 0) {
                help();
            } else {
                String srcYaml = ".";
                String outputSql = ".";
                String sqlType = "postgres";

                if (commandLine.hasOption("h")) {
                    help();
                } else if (commandLine.hasOption("version")) {
                    version();
                } else {
                    if (commandLine.hasOption("y")) {
                        srcYaml = commandLine.getOptionValue("y");
                    }

                    if (commandLine.hasOption("t") && SqlTypes.contains(commandLine.getOptionValue("t"))) {
                        sqlType = commandLine.getOptionValue("t");
                    }

                    Method method = JFixtures.class.getMethod(sqlType, String.class);
                    JFixturesResultImpl result = (JFixturesResultImpl)method.invoke(null, srcYaml);

                    if (commandLine.hasOption("s")) {
                        outputSql = commandLine.getOptionValue("s");
                     }

                    if (commandLine.hasOption("console")) {
                        log.info(result.asString());
                    } else {
                        result.toFile(outputSql);
                    }
                }
            }
        } catch (ParseException e) {
            log.error("Failed to parse command line properties: " + e.getMessage());
            help();
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
