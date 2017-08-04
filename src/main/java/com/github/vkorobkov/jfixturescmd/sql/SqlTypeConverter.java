package com.github.vkorobkov.jfixturescmd.sql;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.ParameterException;
import com.github.vkorobkov.jfixtures.sql.SqlType;
import com.github.vkorobkov.jfixturescmd.utils.EnumUtil;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.Optional;

public class SqlTypeConverter implements IStringConverter<SqlType> {
    @Override
    @SneakyThrows
    public SqlType convert(final String value) {
        Optional<SqlType> sqlType = EnumUtil.valueOf(SqlType.class, value.toUpperCase());

        return sqlType.orElseThrow(() -> {
                    String availableTypes = Arrays.toString(SqlType.values()).toLowerCase();
                    throw new ParameterException("SQL type '" + value + "' is not valid. "
                            + "Available types are: " + availableTypes);
                }
        );
    }
}
