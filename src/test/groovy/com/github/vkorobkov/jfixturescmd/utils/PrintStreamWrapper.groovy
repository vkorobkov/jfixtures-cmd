package com.github.vkorobkov.jfixturescmd.utils

class PrintStreamWrapper extends PrintStream {
    private StringBuilder content = new StringBuilder()

    PrintStreamWrapper(OutputStream out) {
        super(out)
    }

    @Override
    void write(byte[] buf) {
        content.append(new String(buf))
    }

    @Override
    void println(String string) {
        content.append(string)
    }
}
