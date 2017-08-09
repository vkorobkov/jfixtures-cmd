package com.github.vkorobkov.jfixturescmd

class JarIntegrationTest {
    static void main(String[] args) {
        def jarOutput = "java -jar target/jfixtures-cmd-1.0.0.jar --help".execute().text
        assert jarOutput.contains("Usage: JFixtures CMD [options]")
    }
}
