package com.github.vkorobkov.jfixturescmd.cmd

import spock.lang.Specification

class CmdArgsTest extends Specification {
    def "constructor test"() {
        expect:
        new CmdArgs()
    }
}
