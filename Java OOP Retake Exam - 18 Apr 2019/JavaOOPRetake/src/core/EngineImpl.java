package core;

import common.Command;
import common.io.ConsoleReader;
import common.io.ConsoleWriter;
import core.interfaces.Engine;
import core.interfaces.ManagerController;
import common.io.interfaces.InputReader;
import common.io.interfaces.OutputWriter;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class EngineImpl implements Engine {
  private InputReader reader;
  private OutputWriter writer;
    private ManagerController managerController;

    public EngineImpl() {
        this.managerController = new ManagerControllerImpl();
       this.reader =new ConsoleReader();
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

            } catch (IOException | IllegalArgumentException | NullPointerException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                result = e.getMessage();
            }

            this.writer.writeLine(result);
        }
    }

    private String processInput() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException {
        String input = this.reader.readLine();
        String[] tokens = input.split("\\s");

        Command command = Command.valueOf(tokens[0]);
        String[] data = Arrays.stream(tokens).skip(1).toArray(String[]::new);

        String result = null;

        switch (command) {
            case AddPlayer:
                result = managerController.addPlayer(data[0], data[1]);
                break;
            case AddCard:
                result = managerController.addCard(data[0], data[1]);
                break;
            case AddPlayerCard:
                result = managerController.addPlayerCard(data[0], data[1]);
                break;
            case Fight:
                result = managerController.fight(data[0], data[1]);
                break;
            case Report:
                result = managerController.report();
                break;
            case Exit:
                result = "Exit";

        }
        return result;
    }
}
