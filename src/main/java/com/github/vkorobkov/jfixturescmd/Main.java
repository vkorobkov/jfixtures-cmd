package com.github.vkorobkov.jfixturescmd;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        Prompt.write();
        new CmdArgs(args).parse();
    }
}
