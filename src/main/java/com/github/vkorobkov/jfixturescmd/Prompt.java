package com.github.vkorobkov.jfixturescmd;

import com.github.lalyos.jfiglet.FigletFont;

import java.io.IOException;
import java.io.PrintStream;

final class Prompt {
    private Prompt() {
    }

    static void writePrompt(PrintStream printStream) throws IOException {
        final String asciiJFixtures = FigletFont.convertOneLine("JFIXTURES");
        printStream
                .append(asciiJFixtures)
                .append("For more info please visit https://github.com/vkorobkov/jfixtures-cmd");
        printStream.println();
        printStream.close();
    }
}
