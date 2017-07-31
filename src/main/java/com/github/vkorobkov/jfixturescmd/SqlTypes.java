package com.github.vkorobkov.jfixturescmd;

import java.util.EnumSet;
import java.util.stream.Collectors;

public enum SqlTypes {
    POSTGRES,
    MYSQL,
    H2,
    CLICKHOUSE;

    public static boolean contains(String value) {
        return EnumSet.allOf(SqlTypes.class)
                .stream()
                .anyMatch(type -> type.name().equalsIgnoreCase(value));
    }

    public static String getValues() {
        return EnumSet.allOf(SqlTypes.class)
                .stream()
                .map(Enum::name)
                .collect(Collectors.joining(", "));
    }
}
