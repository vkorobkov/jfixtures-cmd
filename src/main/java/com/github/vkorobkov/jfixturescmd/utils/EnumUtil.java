package com.github.vkorobkov.jfixturescmd.utils;

import java.util.EnumSet;
import java.util.Optional;

public final class EnumUtil {
    private EnumUtil() {
    }

    public static <E extends Enum<E>> Optional<E> valueOf(Class<E> enumClazz, String value) {
        return EnumSet.allOf(enumClazz).stream().filter(type -> type.name().equals(value)).findFirst();
    }
}
