package core;

import common.ExceptionMessages;
import common.OutputMessages;
import core.interfaces.Controller;
import models.garages.interfaces.Garage;
import models.garages.GarageImpl;
import models.procedures.Charge;
import models.procedures.interfaces.Procedure;
import models.procedures.Repair;
import models.procedures.Work;
import models.robots.Cleaner;
import models.robots.Housekeeper;
import models.robots.interfaces.Robot;

import java.lang.reflect.InvocationTargetException;

import static common.ExceptionMessages.NON_EXISTING_ROBOT;


public class ControllerImpl implements Controller {
    private Garage garage;
    private Procedure charge;
    private Procedure repair;
    private Procedure work;


    public ControllerImpl() {
        this.garage = new GarageImpl();
        this.charge = new Charge();
        this.repair = new Repair();
        this.work = new Work();
    }

    @Override
    public String manufacture(String robotType, String name,
                              int energy, int happiness, int procedureTime) {

        Robot newRobot;
        if (robotType.equals("Cleaner")) {

            newRobot = new Cleaner(name, energy, happiness, procedureTime);

        } else if (robotType.equals("Housekeeper")) {
            newRobot = new Housekeeper(name, energy, happiness, procedureTime);
        } else {
            throw new IllegalArgumentException(
                    String.format(ExceptionMessages.INVALID_ROBOT_TYPE, robotType));
        }
        this.garage.manufacture(newRobot);


        return String.format(OutputMessages.ROBOT_MANUFACTURED, name);
    }


    @Override
    public String repair(String robotName, int procedureTime) {
        checkIfRobotExist(robotName);
        Robot robot1 = this.garage.getRobots().get(robotName);
        this.repair.doService(robot1, procedureTime);
        return String.format(OutputMessages.REPAIR_PROCEDURE, robotName);
    }

    @Override
    public String work(String robotName, int procedureTime) {
        checkIfRobotExist(robotName);
        Robot robot1 = this.garage.getRobots().get(robotName);
        this.work.doService(robot1, procedureTime);
        return String.format(OutputMessages.WORK_PROCEDURE, robotName, procedureTime);
    }

    @Override
    public String charge(String robotName, int procedureTime) {
        checkIfRobotExist(robotName);
        Robot robot1 = this.garage.getRobots().get(robotName);
        this.charge.doService(robot1, procedureTime);

        return String.format(OutputMessages.CHARGE_PROCEDURE, robotName);
    }

    @Override
    public String sell(String robotName, String ownerName) {
        checkIfRobotExist(robotName);
        this.garage.sell(robotName, ownerName);
        return String.format(OutputMessages.SELL_ROBOT, ownerName, robotName);
    }

    @Override
    public String history(String procedureType) {
        switch (procedureType) {
            case "Repair":
                return this.repair.history();
            case "Charge":
                return this.charge.history();
            case "Work":
                return this.work.history();

        }
        return null;
    }

    private void checkIfRobotExist(String name) {
        if (!this.garage.getRobots().containsKey(name)) {
            throw new IllegalArgumentException(String.format(NON_EXISTING_ROBOT, name));
        }
    }
}
