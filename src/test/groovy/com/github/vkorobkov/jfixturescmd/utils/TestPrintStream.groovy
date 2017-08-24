package com.github.vkorobkov.jfixturescmd.utils

class TestPrintStream extends PrintStream {
    private final ByteArrayOutputStream stream

    TestPrintStream(ByteArrayOutputStream stream) {
        super(stream)
        this.stream = stream
    }

    static TestPrintStream create() {
        new TestPrintStream(new ByteArrayOutputStream())
    }

    static TestPrintStream stubStdOut() {
        def printStream = create()
        System.out = printStream
        printStream
    }

    static boolean contains(String text) {
        stdOut.containsText(text)
    }

    private static TestPrintStream getStdOut() {
        System.out as TestPrintStream
    }

    private boolean containsText(String text) {
        stream.toString().contains(text)
    }
}
