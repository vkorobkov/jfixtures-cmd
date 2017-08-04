package com.github.vkorobkov.jfixturescmd.utils

import com.github.vkorobkov.jfixturescmd.sql.SqlType
import spock.lang.Specification

class EnumUtilTest extends Specification {
    def "constructor test"() {
        expect:
        new EnumUtil()
    }

    def "valueOf equals cases"() {
        expect:
        EnumUtil.valueOf(SqlType.class, value).get() == expected

        where:
        value   | expected
        "MYSQL" | SqlType.MYSQL
    }

    def "valueOf not equals cases"() {
        expect:
        EnumUtil.valueOf(SqlType.class, value) == expected

        where:
        value    | expected
        "mysql"  | Optional.empty()
        "MySql"  | Optional.empty()
        "my_sql" | Optional.empty()
    }
}
