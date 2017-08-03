package com.github.vkorobkov.jfixturescmd;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.ParameterException;
import com.github.vkorobkov.jfixturescmd.utils.EnumUtil;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.Optional;

public class SqlTypesConverter implements IStringConverter<SqlTypes> {
    @Override
    @SneakyThrows
    public SqlTypes convert(final String value) {
        Optional<SqlTypes> sqlType = EnumUtil.valueOf(SqlTypes.class, value.toUpperCase());

        return sqlType.orElseThrow(() -> {
                    String availableTypes = Arrays.toString(SqlTypes.values()).toLowerCase();
                    throw new ParameterException("SQL type '" + value + "' is not valid. "
                            + "Available types are: " + availableTypes);
                }
        );
    }
}
