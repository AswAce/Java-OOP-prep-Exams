package common.io;


public class ConsoleWriter implements common.io.interfaces.OutputWriter {
    @Override
    public void writeLine(String text) {
        System.out.println(text);
    }
}
