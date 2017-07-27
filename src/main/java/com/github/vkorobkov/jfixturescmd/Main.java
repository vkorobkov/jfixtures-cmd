package com.github.vkorobkov.jfixturescmd;

import com.github.lalyos.jfiglet.FigletFont;

import java.io.IOException;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) throws IOException {
        final String asciiJFixtures = FigletFont.convertOneLine("JFIXTURES");
        System.out.println(asciiJFixtures);
        System.out.println("For more info please visit https://github.com/vkorobkov/jfixtures-cmd");
    }
}
