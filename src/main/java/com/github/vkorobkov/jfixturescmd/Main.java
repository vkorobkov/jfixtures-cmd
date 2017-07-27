package com.github.vkorobkov.jfixturescmd;

import static com.github.vkorobkov.jfixturescmd.Prompt.writePrompt;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        writePrompt(System.out);
    }
}
