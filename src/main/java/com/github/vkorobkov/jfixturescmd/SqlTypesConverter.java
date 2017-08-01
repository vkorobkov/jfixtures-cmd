package com.github.vkorobkov.jfixturescmd;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.ParameterException;

public class SqlTypesConverter implements IStringConverter<SqlTypes> {
    @Override
    public SqlTypes convert(final String value) {
        SqlTypes sqlType = SqlTypes.fromString(value);

        if(sqlType == null) {
            throw new ParameterException("SQL type '" + value + "' is not valid. " +
                    "Available types are: " + SqlTypes.getValues().toLowerCase());
        }
        return sqlType;
    }
}
