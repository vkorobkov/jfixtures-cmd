package com.github.vkorobkov.jfixturescmd.cmd

import com.github.vkorobkov.jfixturescmd.utils.TestPrintStream
import spock.lang.Specification

class CmdParserTest extends Specification {
    def printStream
    def cmdParser
    def сmdArgs
    def content

    def setup() {
        printStream = TestPrintStream.create()
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
        content = printStream.getContent()
        then:
        content.contains("Start parsing command line")
        content.contains("Failed to parse command line arguments")
        content.contains("Usage")
    }

    def "parse with help argument"() {
        given:
        String[] args = ["-h"]
        when:
        cmdParser.parse(args)
        content = printStream.getContent()
        then:
        content.contains("Start parsing command line")
        content.contains("Usage")
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
        content = printStream.getContent()
        then:
        content.contains("Start parsing command line")
        content.contains("Fixtures folder:")
        content.contains("SQL type: MYSQL")
        content.contains("SUCCESS (destination file: out.sql)")
        !content.contains("Usage")
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
        content = printStream.getContent()
        then:
        сmdArgs.getSource() == "wrong_path"
        content.contains("Failed to load fixtures: Can not load fixtures from directory: wrong_path")
        content.contains("Usage")
    }
}
