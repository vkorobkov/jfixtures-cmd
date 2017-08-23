package com.github.vkorobkov.jfixturescmd.utils

class TestPrintStream extends PrintStream {
    private static ByteArrayOutputStream outputStream

    TestPrintStream() {
        super(outputStream)
    }

    static TestPrintStream create() {
        outputStream = new ByteArrayOutputStream()
        return new TestPrintStream()
    }

    static String getContent() {
       return outputStream.toString()
    }
}
