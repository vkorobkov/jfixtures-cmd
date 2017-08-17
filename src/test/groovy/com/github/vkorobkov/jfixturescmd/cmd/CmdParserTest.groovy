package com.github.vkorobkov.jfixturescmd.cmd

import spock.lang.Specification

class CmdParserTest extends Specification {
    def printStream
    def cmdParser
    def сmdArgs

    def setup() {
        printStream = Mock(PrintStream)
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
        2 * printStream.write(_)
        2 * printStream.flush()
        1 * printStream.println({ it.contains("Usage") })
    }

    def "parse with help argument"() {
        given:
        String[] args = ["-h"]
        when:
        cmdParser.parse(args)
        then:
        1 * printStream.write(_)
        1 * printStream.flush()
        1 * printStream.println({ it.contains("Usage") })
        сmdArgs.isHelp()
    }

    def "parse with all valid arguments"() {
        given:
        String[] args = ["-src", "src/test/resources/fixtures", "-dst", "out.sql", "-type", "mysql"]
        when:
        cmdParser.parse(args)
        then:
        (1.._) * printStream.write(_)
        (1.._) * printStream.flush()
        0 * printStream.println({ it.contains("Usage") })
        сmdArgs.sqlType.toString() == "MYSQL"
        сmdArgs.source.toString() == "src/test/resources/fixtures"
        сmdArgs.destination.toString() == "out.sql"
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
}
