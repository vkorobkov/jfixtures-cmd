package com.github.vkorobkov.jfixturescmd

import spock.lang.Specification

import static com.github.vkorobkov.jfixturescmd.Main.main

class MainTest extends Specification {
    def prompt = GroovyMock(Prompt)
    def printStream = Mock(PrintStream)
    String [] args = new String ["1"]

    def "constructor test"() {
        expect:
        new Main()
    }

    def "main method test"() {
        expect:
        main("--help")
    }
}
