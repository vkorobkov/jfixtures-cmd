package com.github.vkorobkov.jfixturescmd;

import com.github.vkorobkov.jfixturescmd.cmd.CmdParser;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        Prompt.write();
        new CmdParser().parse(args);
    }
}
