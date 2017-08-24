package com.github.vkorobkov.jfixturescmd

import com.github.vkorobkov.jfixturescmd.utils.TestPrintStream
import spock.lang.Specification

import static com.github.vkorobkov.jfixturescmd.Prompt.write

class PromptTest extends Specification {
    def setup() {
        TestPrintStream.stubStdOut()
    }

    def "constructor test"() {
        expect:
        new Prompt()
    }

    def "should write prompt"() {
        when:
        write()
        then:
        TestPrintStream.contains("For more info please visit \${project.scm.url}")
    }
}
