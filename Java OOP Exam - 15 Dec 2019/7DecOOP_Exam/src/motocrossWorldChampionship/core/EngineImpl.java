package motocrossWorldChampionship.core;

import motocrossWorldChampionship.core.interfaces.ChampionshipController;
import motocrossWorldChampionship.core.interfaces.Engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class EngineImpl implements Engine {
    private BufferedReader reader;
    private ChampionshipController championshipController;

    public EngineImpl() {
        this.championshipController = new ChampionshipControllerImpl();
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {
        while (true) {
            String result = null;
            try {
                result = processInput();

                if (result.equals("Exit")) {
                    break;
                }
            } catch (NullPointerException | IllegalArgumentException | IOException e) {
                result = e.getMessage();
            }

            System.out.println(result);
        }
    }

    private String processInput() throws IOException {
        String input = this.reader.readLine();
        String[] tokens = input.split("\\s+");


        String result = null;
        String[] data = Arrays.stream(tokens).skip(1).toArray(String[]::new);

        switch (tokens[0]) {
            case "CreateRider":
                result = this.championshipController.createRider(data[0]);
                break;
            case "CreateMotorcycle":
                result = this.championshipController.
                        createMotorcycle(data[0], data[1], Integer.parseInt(data[2]));
                break;
            case "AddMotorcycleToRider":
                result = this.championshipController.addMotorcycleToRider(data[0], data[1]);
                break;
            case "AddRiderToRace":
                result = this.championshipController.addRiderToRace(data[0], data[1]);
                break;
            case "CreateRace":
                result = this.championshipController.createRace(data[0], Integer.parseInt(data[1]));
                break;

            case "StartRace":
                result = this.championshipController.startRace(data[0]);
                break;

            case "End":
                result = "Exit";
                break;
        }
        return result;
    }
}
