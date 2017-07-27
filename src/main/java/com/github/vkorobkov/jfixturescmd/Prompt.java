package com.github.vkorobkov.jfixturescmd;

import com.github.lalyos.jfiglet.FigletFont;
import lombok.SneakyThrows;

import java.io.PrintStream;

final class Prompt {
    private Prompt() {
    }

    @SneakyThrows
    static void writePrompt(PrintStream printStream) {
        final String asciiJFixtures = FigletFont.convertOneLine("JFIXTURES");
        printStream
                .append(asciiJFixtures)
                .append("For more info please visit https://github.com/vkorobkov/jfixtures-cmd");
        printStream.println();
    }
}
