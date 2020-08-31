import core.MachineFactoryImpl;
import core.MachinesManagerImpl;

import core.PilotFactoryImpl;
import core.interfaces.MachineFactory;
import core.interfaces.MachinesManager;
import core.interfaces.PilotFactory;
import entities.interfaces.Machine;
import entities.interfaces.Pilot;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PilotFactory pilotFactory = new PilotFactoryImpl();
        MachineFactory machineFactory = new MachineFactoryImpl();
        Map<String, Pilot> pilots = new LinkedHashMap<>();

        Map<String, Machine> machines = new LinkedHashMap<>();


        MachinesManager machinesManager = new MachinesManagerImpl
                (pilotFactory,
                        machineFactory, pilots, machines
                );

        String command = scanner.nextLine();
        while (!command.equals("Over")) {
            String[] token = command.split("\\s+");
            switch (token[0]) {
                case "Hire":
                    System.out.println(machinesManager.hirePilot(token[1]));
                    break;
                case "Report":
                    System.out.println(machinesManager.pilotReport(token[1]));
                    break;
                case "ManufactureTank":
                    System.out.println(machinesManager.manufactureTank(token[1],
                            Double.parseDouble(token[2]), Double.parseDouble(token[3])));
                    break;
                case "ManufactureFighter":
                    System.out.println(
                            machinesManager.manufactureFighter(token[1],
                                    Double.parseDouble(token[2]), Double.parseDouble(token[3])));
                    break;

                case "AggressiveMode":
                    System.out.println(machinesManager.toggleFighterAggressiveMode(token[1]));
                    break;
                case "DefenseMode":
                    System.out.println(machinesManager.toggleTankDefenseMode(token[1]));
                    break;
                case "Attack":
                    System.out.println(machinesManager.attackMachines(token[1], token[2]));
                    break;

                case "Engage":
                    System.out.println(machinesManager.engageMachine(token[1], token[2]));
                    break;

            }


            command = scanner.nextLine();
        }

    }
}
