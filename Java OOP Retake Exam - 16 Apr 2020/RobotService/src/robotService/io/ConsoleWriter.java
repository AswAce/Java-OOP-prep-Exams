package io;


public class ConsoleWriter implements io.interfaces.OutputWriter {
    @Override
    public void writeLine(String text) {
        System.out.println(text);
    }
}
