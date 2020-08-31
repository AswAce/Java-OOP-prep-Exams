package core;


import common.Command;
import core.interfaces.Controller;
import core.interfaces.Engine;
import io.ConsoleReader;
import io.ConsoleWriter;
import io.interfaces.InputReader;
import io.interfaces.OutputWriter;

import java.io.IOException;
import java.util.Arrays;

public class EngineImpl implements Engine {
    private InputReader reader;
    private OutputWriter writer;
    private Controller controller;

    public EngineImpl() {
        this.controller = new ControllerImpl();
        this.reader = new ConsoleReader();
        this.writer = new ConsoleWriter();
    }

    @Override
    public void run() {
        while (true) {
            String result = null;
            try {
                result = processInput();

                if ("Exit".equals(result)) {
                    break;
                }

            } catch (IOException | IllegalArgumentException | NullPointerException e) {
                result = e.getMessage();
            }

            this.writer.writeLine(result);
        }
    }

    private String processInput() throws IOException {
        String input = this.reader.readLine();
        String[] tokens = input.split("\\s");

        Command command = Command.valueOf(tokens[0]);
        String[] data = Arrays.stream(tokens).skip(1).toArray(String[]::new);

        String result = null;

        switch (command) {
            case Manufacture:
                result = manufacture(data);
                break;
            case Repair:
                result = repair(data);
                break;
            case Work:
                result = work(data);
                break;
            case Charge:
                result = charge(data);
                break;
            case Sell:
                result = sell(data);
                break;
            case History:
                result = history(data);
                break;
            case Exit:
                result = "Exit";
                break;
        }

        return result;
    }

    private String manufacture(String[] data) {
        // TODO
        return this.controller.manufacture(data[0], data[1],
                Integer.parseInt(data[2]), Integer.parseInt(data[3]), Integer.parseInt(data[04]));
    }

    private String repair(String[] data) {

        return this.controller.repair(data[0], Integer.parseInt(data[1]));
    }

    private String work(String[] data) {

        return this.controller.work(data[0], Integer.parseInt(data[1]));
    }

    private String charge(String[] data) {

        return this.controller.charge(data[0], Integer.parseInt(data[1]));
    }

    private String sell(String[] data) {

        return this.controller.sell(data[0], data[1]);
    }

    private String history(String[] data) {

        return this.controller.history(data[0]);
    }
}
