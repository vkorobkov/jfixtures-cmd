package com.github.vkorobkov.jfixturescmd

import org.spockframework.util.TeePrintStream
import spock.lang.Specification

import static com.github.vkorobkov.jfixturescmd.Prompt.writePrompt

class PromptTest extends Specification {
    def printStream = Mock(PrintStream)
    def teePrintStream = new TeePrintStream(printStream)

    def "constructor test"() {
        expect:
        new Prompt()
    }

    def "should write prompt"() {
        when:
        writePrompt(teePrintStream)
        then:
        2 * printStream.append(_)
        1 * printStream.println()
    }
}
