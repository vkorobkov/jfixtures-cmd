package com.github.vkorobkov.jfixturescmd.sql

import spock.lang.Specification

class SqlTypeTest extends Specification {
    def "SqlType positive cases"(String value, expected) {
        expect:
        SqlType.valueOf(value) == expected

        where:
        value        | expected
        "POSTGRES"   | SqlType.POSTGRES
        "MYSQL"      | SqlType.MYSQL
        "H2"         | SqlType.H2
        "CLICKHOUSE" | SqlType.CLICKHOUSE
    }

    def "SqlType throws exception when wrong value is provided"() {
        when:
        SqlType.valueOf("wrong_type")

        then:
        thrown(IllegalArgumentException)
    }
}
