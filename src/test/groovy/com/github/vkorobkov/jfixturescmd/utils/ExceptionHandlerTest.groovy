package com.github.vkorobkov.jfixturescmd.utils

import com.beust.jcommander.ParameterException
import com.github.vkorobkov.jfixtures.loader.LoaderException
import com.github.vkorobkov.jfixtures.processor.ProcessorException
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

    def "handle LoaderException from core project"() {
        given:
        def exception = new LoaderException("Can not load fixtures from directory: wrong_path")
        when:
        ExceptionHandler.handleException(exception)
        then:
        1 * printStream.write(_)
        1 * printStream.flush()
    }

    def "handle ProcessorException from core project"() {
        given:
        def exception = new ProcessorException("Failed to process fixtures")
        when:
        ExceptionHandler.handleException(exception)
        then:
        1 * printStream.write(_)
        1 * printStream.flush()
    }

    def "handle ParameterException"() {
        given:
        def exception = new ParameterException("Failed to parse command line arguments")
        when:
        ExceptionHandler.handleException(exception)
        then:
        1 * printStream.write(_)
        1 * printStream.flush()
    }

    def "handle unexpected exception"() {
        given:
        def exception = new IOException("IOException")
        when:
        ExceptionHandler.handleException(exception)
        then:
        1 * printStream.write(_)
        1 * printStream.flush()
    }
}
