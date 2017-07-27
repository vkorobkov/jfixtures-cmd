package com.github.vkorobkov.jfixturescmd;

import java.io.IOException;

import static com.github.vkorobkov.jfixturescmd.Prompt.writePrompt;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) throws IOException {
        writePrompt(System.out);
    }
}
