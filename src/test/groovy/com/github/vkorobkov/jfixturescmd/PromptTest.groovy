package com.github.vkorobkov.jfixturescmd

import com.github.vkorobkov.jfixturescmd.utils.TestPrintStream
import spock.lang.Specification

import static com.github.vkorobkov.jfixturescmd.Prompt.write

class PromptTest extends Specification {
    def printStream

    def setup() {
        printStream = TestPrintStream.create()
        System.out = printStream
    }

    def "constructor test"() {
        expect:
        new Prompt()
    }

    def "should write prompt"() {
        when:
        write()
        then:
        printStream.getContent().contains("For more info please visit \${project.scm.url}")
    }
}
