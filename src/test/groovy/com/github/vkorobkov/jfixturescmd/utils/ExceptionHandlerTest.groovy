package com.github.vkorobkov.jfixturescmd.utils

import com.beust.jcommander.ParameterException
import com.github.vkorobkov.jfixtures.loader.LoaderException
import com.github.vkorobkov.jfixtures.processor.ProcessorException
import spock.lang.Specification

import java.nio.file.NoSuchFileException

class ExceptionHandlerTest extends Specification {
    def setup() {
        TestPrintStream.stubStdOut()
    }

    def "constructor test"() {
        expect:
        new ExceptionHandler()
    }

    def "handle LoaderException from core project"() {
        given:
        def exception = new LoaderException("LoaderException")
        when:
        ExceptionHandler.handleException(exception)
        then:
        TestPrintStream.contains("Failed to load fixtures: LoaderException")
    }

    def "handle NoSuchFileException from core project"() {
        given:
        def exception = new NoSuchFileException("NoSuchFileException")
        when:
        ExceptionHandler.handleException(exception)
        then:
        TestPrintStream.contains("Failed to load fixtures: NoSuchFileException")
    }

    def "handle ProcessorException from core project"() {
        given:
        def exception = new ProcessorException("ProcessorException")
        when:
        ExceptionHandler.handleException(exception)
        then:
        TestPrintStream.contains("Failed to process fixtures: ProcessorException")
    }

    def "handle ParameterException"() {
        given:
        def exception = new ParameterException("ParameterException")
        when:
        ExceptionHandler.handleException(exception)
        then:
        TestPrintStream.contains("Failed to parse command line arguments: ParameterException")
    }

    def "handle unexpected exception"() {
        given:
        def exception = new IOException("IOException")
        when:
        ExceptionHandler.handleException(exception)
        then:
        TestPrintStream.contains("IOException")
    }
}
