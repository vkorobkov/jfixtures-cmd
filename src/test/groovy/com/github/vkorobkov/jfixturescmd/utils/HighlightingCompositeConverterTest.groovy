package com.github.vkorobkov.jfixturescmd.utils

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.spi.ILoggingEvent
import spock.lang.Specification

import static ch.qos.logback.core.pattern.color.ANSIConstants.BOLD
import static ch.qos.logback.core.pattern.color.ANSIConstants.DEFAULT_FG
import static ch.qos.logback.core.pattern.color.ANSIConstants.RED_FG

class HighlightingCompositeConverterTest extends Specification {
    def event = Mock(ILoggingEvent)
    def converter = new HighlightingCompositeConverter()

    def "constructor test"() {
        expect:
        new HighlightingCompositeConverter()
    }

    def "should see red bold color for errors"() {
        given:
        1 * event.getLevel() >> Level.ERROR
        when:
        converter.getForegroundColorCode(event)
        then:
        BOLD + RED_FG
    }

    def "should see red color for warnings"() {
        given:
        1 * event.getLevel() >> Level.WARN
        when:
        converter.getForegroundColorCode(event)
        then:
        RED_FG
    }

    def "should see default color for info"() {
        given:
        1 * event.getLevel() >> Level.INFO
        when:
        converter.getForegroundColorCode(event)
        then:
        DEFAULT_FG
    }
}
