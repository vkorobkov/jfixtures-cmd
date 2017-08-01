package com.github.vkorobkov.jfixturescmd;

import java.util.EnumSet;
import java.util.stream.Collectors;

public enum SqlTypes {
    POSTGRES,
    MYSQL,
    H2,
    CLICKHOUSE;

    public static String getValues() {
        return EnumSet.allOf(SqlTypes.class)
                .stream()
                .map(Enum::name)
                .collect(Collectors.joining(", "));
    }

    public static SqlTypes fromString(final String value) {
        return EnumSet.allOf(SqlTypes.class)
                .stream()
                .filter(type -> type.name().equalsIgnoreCase(value))
                .findFirst().orElse(null);
    }
}
