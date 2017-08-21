package com.github.vkorobkov.jfixturescmd.utils

import com.github.vkorobkov.jfixtures.loader.LoaderException
import spock.lang.Specification

class ExceptionHandlerTest extends Specification {
    def printStream

    def setup() {
        printStream = Mock(PrintStream)
        System.out = printStream
    }

    def "constructor test"() {
        expect:
        new ExceptionHandler()
    }

    def "handle exception from core project"() {
        given:
        def exception = new LoaderException("Can not load fixtures from directory: wrong_path")
        when:
        ExceptionHandler.handleException(exception)
        then:
        1 * printStream.write(_)
        1 * printStream.flush()
    }
}
