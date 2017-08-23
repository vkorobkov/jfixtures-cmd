package com.github.vkorobkov.jfixturescmd

import com.github.vkorobkov.jfixturescmd.utils.PrintStreamWrapper
import spock.lang.Specification

import static com.github.vkorobkov.jfixturescmd.Prompt.write

class PromptTest extends Specification {
    def printStream

    def setup() {
        printStream = new PrintStreamWrapper(System.out)
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
        printStream.content.contains("For more info please visit \${project.scm.url}")
    }
}
