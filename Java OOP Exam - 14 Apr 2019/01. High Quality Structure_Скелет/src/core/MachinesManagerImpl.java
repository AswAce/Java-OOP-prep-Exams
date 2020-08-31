package core;

import common.OutputMessages;
import core.interfaces.MachineFactory;
import core.interfaces.PilotFactory;
import core.interfaces.MachinesManager;

import entities.interfaces.FighterImpl;
import entities.interfaces.TankImpl;
import entities.interfaces.Machine;
import entities.interfaces.Pilot;

import java.util.Map;

public class MachinesManagerImpl implements MachinesManager {
    private PilotFactory pilotFactory;
    private MachineFactory machineFactory;
    private Map<String, Pilot> pilots;
    private Map<String, Machine> machines;


    public MachinesManagerImpl(PilotFactory pilotFactory, MachineFactory machineFactory, Map<String, Pilot> pilots, Map<String, Machine> machines) {
        this.pilots = pilots;
        this.machines = machines;
        this.pilotFactory = pilotFactory;
        this.machineFactory = machineFactory;

    }


    @Override
    public String hirePilot(String name) {
        if (pilots.containsKey(name)) {
            return String.format(OutputMessages.pilotExists, name);
        } else {
            pilots.put(name, pilotFactory.createPilot(name));
            return String.format(OutputMessages.pilotHired, name);
        }
    }

    @Override
    public String manufactureTank(String name, double attackPoints, double defensePoints) {
        if (machines.containsKey(name)) {
            return String.format(OutputMessages.machineExists, name);
        } else {
            machines.put(name, machineFactory.createTank(name, attackPoints, defensePoints));
            return String.format(OutputMessages.tankManufactured, name, attackPoints, defensePoints);
        }

    }

    @Override
    public String manufactureFighter(String name, double attackPoints, double defensePoints) {
        if (machines.containsKey(name)) {
            return String.format(OutputMessages.machineExists, name);
        } else {
            machines.put(name, machineFactory.createFighter(name, attackPoints, defensePoints));
            return String.format(OutputMessages.fighterManufactured, name, attackPoints, defensePoints);
        }
    }

    @Override
    public String engageMachine(String selectedPilotName, String selectedMachineName) {
        if (!pilots.containsKey(selectedPilotName)) {
            return String.format(OutputMessages.pilotNotFound, selectedPilotName);
        }

        if (!machines.containsKey(selectedMachineName)) {
            return String.format(OutputMessages.machineNotFound, selectedMachineName);
        }


        if (machines.get(selectedMachineName).getPilot() != null) {
            return String.format(OutputMessages.machineHasPilotAlready, selectedMachineName);
        } else {
            pilots.get(selectedPilotName).addMachine(machines.get(selectedMachineName));

            machines.get(selectedMachineName).setPilot(pilots.get(selectedPilotName));

            return String.format(OutputMessages.machineEngaged, selectedPilotName, selectedMachineName);
        }


    }

    @Override
    public String attackMachines(String attackingMachineName, String defendingMachineName) {
        if (machines.containsKey(attackingMachineName) &&
                machines.containsKey(defendingMachineName)) {
            Machine attacking = machines.get(attackingMachineName);
            Machine defencing = machines.get(defendingMachineName);
            attacking.attack(defendingMachineName);
            if (attacking.getAttackPoints() >= defencing.getDefensePoints()) {
                defencing.setHealthPoints(defencing.getHealthPoints() - (attacking.getAttackPoints() - defencing.getDefensePoints()));
                if (defencing.getHealthPoints() < 0) {
                    defencing.setHealthPoints(0);
                }

            }
            return String.format(OutputMessages.attackSuccessful, defendingMachineName,
                    attackingMachineName, defencing.getHealthPoints());
        } else if (!machines.containsKey(attackingMachineName)) {
            return String.format(OutputMessages.machineNotFound, attackingMachineName);
        } else {
            return String.format(OutputMessages.machineNotFound, defendingMachineName);
        }

    }

    @Override
    public String pilotReport(String pilotName) {
        if (!pilots.containsKey(pilotName)) {
            return String.format(OutputMessages.pilotNotFound, pilotName);
        }
        return pilots.get(pilotName).report();
    }

    @Override
    public String toggleFighterAggressiveMode(String fighterName) {
        if (!machines.containsKey(fighterName)) {
            return String.format(OutputMessages.machineNotFound, fighterName);
        }
        if (!(machines.get(fighterName) instanceof FighterImpl)) {
            return String.format(OutputMessages.notSupportedOperation, fighterName);
        } else {
            FighterImpl fighter = (FighterImpl) machines.get(fighterName);
            fighter.toggleAggressiveMode();
            return String.format(OutputMessages.fighterOperationSuccessful, fighterName);
        }
    }

    @Override
    public String toggleTankDefenseMode(String tankName) {

        if (!(machines.get(tankName) instanceof TankImpl)) {
            return String.format(OutputMessages.notSupportedOperation, tankName);
        }
        if (!machines.containsKey(tankName)) {
            return String.format(OutputMessages.machineNotFound, tankName);
        } else {
            TankImpl tank = (TankImpl) machines.get(tankName);
            tank.toggleDefenseMode();
            return String.format(OutputMessages.tankOperationSuccessful, tankName);
        }
    }
}

