package com.github.vkorobkov.jfixturescmd.cmd

import com.beust.jcommander.ParameterException
import com.github.vkorobkov.jfixturescmd.utils.TestPrintStream
import spock.lang.Specification

class CmdParserTest extends Specification {
    def cmdParser
    def сmdArgs

    def setup() {
        TestPrintStream.stubStdOut()
        cmdParser = new CmdParser()
        сmdArgs = cmdParser.cmdArgs
    }

    def "constructor test"() {
        expect:
        new CmdParser()
    }

    def "parse without arguments"() {
        when:
        cmdParser.parse()
        then:
        TestPrintStream.contains("Start parsing command line")
        TestPrintStream.contains("Failed to parse command line arguments")
        TestPrintStream.contains("Usage")
    }

    def "parse with help argument"() {
        given:
        String[] args = ["-h"]
        when:
        cmdParser.parse(args)
        then:
        TestPrintStream.contains("Start parsing command line")
        TestPrintStream.contains("Usage")
        сmdArgs.help
    }

    def "parse with all valid arguments"() {
        given:
        String[] args = ["-src", "src/test/resources/fixtures", "-dst", "out.sql", "-type", "mysql"]
        when:
        cmdParser.parse(args)
        then:
        сmdArgs.destinationType.toString() == "mysql"
        сmdArgs.source.toString() == "src/test/resources/fixtures"
        сmdArgs.destination.toString() == "out.sql"
    }

    def "parse with all valid arguments - test messages"() {
        given:
        String[] args = ["-src", "src/test/resources/fixtures", "-dst", "out.sql", "-type", "mysql"]
        when:
        cmdParser.parse(args)
        then:
        TestPrintStream.contains("Start parsing command line")
        TestPrintStream.contains("Fixtures folder:")
        TestPrintStream.contains("SQL type: mysql")
        TestPrintStream.contains("SUCCESS (destination file: out.sql)")
        !TestPrintStream.contains("Usage")
    }

    def "null type when type is not valid"() {
        given:
        String[] args = ["-src", "src/test/resources/fixtures", "-dst", "out.sql", "-type", "wrong_type"]
        when:
        cmdParser.parse(args)
        then:
        сmdArgs.destinationType == "wrong_type"
    }

    def "generate SQL for all dialects"(String dialect, expected) {
        expect:
        String[] args = ["-src", "src/test/resources/fixtures", "-dst", "out.sql", "-type", dialect]
        cmdParser.parse(args)
        сmdArgs.destinationType.toString() == expected

        where:
        dialect | expected
        "mysql" | "mysql"
        "mssql" | "mssql"
        "sql99" | "sql99"
    }

    def "fixtures folder or file path is wrong"() {
        given:
        String[] args = ["-src", "wrong_path", "-dst", "out.sql", "-type", "mysql"]
        when:
        cmdParser.parse(args)
        then:
        сmdArgs.source == "wrong_path"
        TestPrintStream.contains("Failed to load fixtures: wrong_path")
        TestPrintStream.contains("Usage")
    }

    def "export to XML"() {
        given:
        String[] args = ["-src", "src/test/resources/fixtures", "-dst", "out.xml", "-type", "xml"]
        when:
        cmdParser.parse(args)
        then:
        сmdArgs.source.toString() == "src/test/resources/fixtures"
        сmdArgs.destination.toString() == "out.xml"
    }

    def "export to XML - test messages"() {
        given:
        String[] args = ["-src", "src/test/resources/fixtures", "-dst", "out.xml", "-type", "xml"]
        when:
        cmdParser.parse(args)
        then:
        TestPrintStream.contains("Start parsing command line")
        TestPrintStream.contains("Fixtures folder:")
        TestPrintStream.contains("Export type: XML")
        TestPrintStream.contains("SUCCESS (destination file: out.xml)")
        !TestPrintStream.contains("Usage")
    }

    def "should convert valid value to SQL type"() {
        expect:
        cmdParser.getSqlDialect("mysql").toString() == "MYSQL"
        cmdParser.getSqlDialect("MySql").toString() == "MYSQL"
        cmdParser.getSqlDialect("MYSQL").toString() == "MYSQL"
    }

    def "should throw an exception when type in not found"() {
        when:
        cmdParser.getSqlDialect("wrong_type")

        then:
        def e = thrown(ParameterException)
        e.message == "Output type 'wrong_type' is not valid. Possible output types are: [mysql, mssql, sql99, xml]"
    }
}
