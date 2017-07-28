package com.github.vkorobkov.jfixturescmd

import spock.lang.Specification

import static com.github.vkorobkov.jfixturescmd.Prompt.write

class PromptTest extends Specification {
    def printStream = Mock(PrintStream)

    def "constructor test"() {
        expect:
        new Prompt()
    }

    def "should write prompt"() {
        given:
        System.setOut(printStream)
        when:
        write()
        then:
        2 * printStream.write(_)
        2 * printStream.flush()
    }
}
