package com.github.vkorobkov.jfixturescmd;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        Prompt.write();
        new CmdParser().parse(args);
    }
}
