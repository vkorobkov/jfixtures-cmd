package com.github.vkorobkov.jfixturescmd.utils

import spock.lang.Specification

class PropertiesReaderTest extends Specification {
    def "constructor test"() {
        expect:
        new PropertiesReader()
    }

    def "properties test"() {
        expect:
        PropertiesReader.PROJECT_NAME == "\${project.name}"
        PropertiesReader.PROJECT_VERSION == "\${project.version}"
        PropertiesReader.PROJECT_LINK.contains("https://github.com/vkorobkov/jfixtures-cmd")
        PropertiesReader.J_FIXTURES_VERSION == "\${jfixtures.version}"
    }
}
