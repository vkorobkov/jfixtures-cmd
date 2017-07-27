package com.github.vkorobkov.jfixturescmd

import spock.lang.Specification

import static com.github.vkorobkov.jfixturescmd.Main.main

class MainTest extends Specification {
    def "constructor test"() {
        expect:
        new Main()
    }

    def "main method test"() {
        expect:
        main("--help")
    }
}
