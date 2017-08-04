package com.github.vkorobkov.jfixturescmd.cmd

import com.github.vkorobkov.jfixturescmd.cmd.CmdArgs
import spock.lang.Specification

class CmdArgsTest extends Specification {
    def "constructor test"() {
        expect:
        new CmdArgs()
    }
}
