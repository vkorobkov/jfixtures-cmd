package com.github.vkorobkov.jfixturescmd.cmd;

import com.beust.jcommander.Parameter;
import lombok.Getter;

@Getter
final class CmdArgs {
    @Parameter(names = "-type", description = "SQL/XML type. Available types: [mysql, mssql, sql99, xml]",
            required = true, order = 0)
    private String destinationType;

    @Parameter(names = "-src", description = "Source YAML fixtures folder path", required = true, order = 1)
    private String source;

    @Parameter(names = "-dst", description = "Destination SQL/XML file path", required = true, order = 2)
    private String destination;

    @Parameter(names = { "-h", "--help" }, help = true, description = "Print help message")
    private boolean help;
}
