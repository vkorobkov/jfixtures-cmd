package com.github.vkorobkov.jfixturescmd;

import com.beust.jcommander.Parameter;
import lombok.Getter;

@Getter
final class CmdArgs {
    @Parameter(names = "-type", description = "SQL type", required = true,
            converter = SqlTypesConverter.class, order = 0)
    private SqlTypes sqlType;

    @Parameter(names = "-src", description = "Source YAML folder path", required = true, order = 1)
    private String source;

    @Parameter(names = "-dst", description = "Destination SQL file path", required = true, order = 2)
    private String destination;

    @Parameter(names = { "-h", "--help" }, help = true)
    private boolean help;

    private CmdArgs() {
    }

    private static final class CmdArgsHelper {
        private CmdArgsHelper() {
        }
        private static final CmdArgs INSTANCE = new CmdArgs();
    }

    static CmdArgs getInstance() {
        return CmdArgsHelper.INSTANCE;
    }
}
