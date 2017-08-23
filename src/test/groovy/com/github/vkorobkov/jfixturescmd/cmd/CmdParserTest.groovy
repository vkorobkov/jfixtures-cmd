package com.github.vkorobkov.jfixturescmd.cmd

import com.github.vkorobkov.jfixturescmd.utils.PrintStreamWrapper
import spock.lang.Specification

class CmdParserTest extends Specification {
    def printStream
    def cmdParser
    def сmdArgs

    def setup() {
        printStream = new PrintStreamWrapper(System.out)
        cmdParser = new CmdParser()
        сmdArgs = cmdParser.cmdArgs
        System.out = printStream
    }

    def "constructor test"() {
        expect:
        new CmdParser()
    }

    def "parse without arguments"() {
        when:
        cmdParser.parse()
        then:
        printStream.content.contains("Start parsing command line")
        printStream.content.contains("Failed to parse command line arguments")
        printStream.content.contains("Usage")
    }

    def "parse with help argument"() {
        given:
        String[] args = ["-h"]
        when:
        cmdParser.parse(args)
        then:
        printStream.content.contains("Start parsing command line")
        printStream.content.contains("Usage")
        сmdArgs.isHelp()
    }

    def "parse with all valid arguments"() {
        given:
        String[] args = ["-src", "src/test/resources/fixtures", "-dst", "out.sql", "-type", "mysql"]
        when:
        cmdParser.parse(args)
        then:
        сmdArgs.sqlType.toString() == "MYSQL"
        сmdArgs.source.toString() == "src/test/resources/fixtures"
        сmdArgs.destination.toString() == "out.sql"
    }

    def "parse with all valid arguments - test messages"() {
        given:
        String[] args = ["-src", "src/test/resources/fixtures", "-dst", "out.sql", "-type", "mysql"]
        when:
        cmdParser.parse(args)
        then:
        printStream.content.contains("Start parsing command line")
        printStream.content.contains("Fixtures folder:")
        printStream.content.contains("SQL type: MYSQL")
        printStream.content.contains("SUCCESS (destination file: out.sql)")
        !printStream.content.contains("Usage")
    }

    def "null type when type is not valid"() {
        given:
        String[] args = ["-src", "src/test/resources/fixtures", "-dst", "out.sql", "-type", "wrong_type"]
        when:
        cmdParser.parse(args)
        then:
        сmdArgs.sqlType == null
    }

    def "generate SQL for all dialects"(String dialect, expected) {
        expect:
        String[] args = ["-src", "src/test/resources/fixtures", "-dst", "out.sql", "-type", dialect]
        cmdParser.parse(args)
        сmdArgs.sqlType.toString() == expected

        where:
        dialect      | expected
        "mysql"      | "MYSQL"
        "postgres"   | "POSTGRES"
        "h2"         | "H2"
        "clickhouse" | "CLICKHOUSE"
    }

    def "fixtures folder is wrong test"() {
        given:
        String[] args = ["-src", "wrong_path", "-dst", "out.sql", "-type", "mysql"]
        when:
        cmdParser.parse(args)
        then:
        сmdArgs.getSource() == "wrong_path"
        printStream.content.contains("Failed to load fixtures: Can not load fixtures from directory: wrong_path")
        printStream.content.contains("Usage")
    }
}
