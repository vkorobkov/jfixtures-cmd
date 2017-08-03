package com.github.vkorobkov.jfixturescmd

import spock.lang.Specification

class CmdParserTest extends Specification {
    def printStream
    def cmdParser

    def setup() {
        printStream = Mock(PrintStream)
        cmdParser = new CmdParser()
        System.out = printStream
    }

    def "parse without arguments"() {
        when:
        cmdParser.parse()
        then:
        1 * printStream.write(_)
        1 * printStream.flush()
        1 * printStream.println({it.contains("Usage")})
    }

    def "parse with help argument"() {
        given:
        String[] args = ["-h"]
        when:
        cmdParser.parse(args)
        then:
        0 * printStream.write(_)
        0 * printStream.flush()
        1 * printStream.println({it.contains("Usage")})
    }

    def "parse with all valid argument"() {
        given:
        String[] args = ["-src", "src/test/resources/fixtures", "-dst", "out.sql", "-type", "mysql"]
        when:
        cmdParser.parse(args)
        then:
        0 * printStream.write(_)
        0 * printStream.flush()
        0 * printStream.println({it.contains("Usage")})
    }
}
