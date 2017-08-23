package com.github.vkorobkov.jfixturescmd.utils

class PrintStreamWrapper extends PrintStream {
    private String content

    PrintStreamWrapper(OutputStream out) {
        super(out)
    }

    @Override
    void write(byte[] buf) {
        content = new String(buf)
    }
}
