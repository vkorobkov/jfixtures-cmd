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
        System.out = printStream
        when:
        write()
        then:
        (1.._) * printStream.write(_)
        (1.._) * printStream.flush()
    }
}
