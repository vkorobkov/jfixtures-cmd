package com.github.vkorobkov.jfixturescmd

import com.beust.jcommander.ParameterException
import spock.lang.Specification

class SqlTypeConverterTest extends Specification {
    def sqlTypeConverter = new SqlTypeConverter()

    def "constructor test"() {
        expect:
        new SqlTypeConverter()
    }

    def "should convert valid value to SQL type"() {
        expect:
        sqlTypeConverter.convert("mysql").toString() == "MYSQL"
        sqlTypeConverter.convert("MySql").toString() == "MYSQL"
        sqlTypeConverter.convert("MYSQL").toString() == "MYSQL"
    }

    def "should throw an exception when type in not found"() {
        when:
        sqlTypeConverter.convert("wrong_type")

        then:
        def e = thrown(ParameterException)
        e.message == "SQL type 'wrong_type' is not valid. Available types are: [postgres, mysql, h2, clickhouse]"
    }
}
