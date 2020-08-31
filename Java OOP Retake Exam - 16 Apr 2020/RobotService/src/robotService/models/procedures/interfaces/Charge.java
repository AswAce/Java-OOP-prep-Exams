package models.procedures.interfaces;

import models.robots.interfaces.Robot;

public class Charge extends BaseProcedure {
    private static final int HAPPINESS_ADD = 12;
    private static final int ENERGY_ADD = 10;

    @Override
    public void doService(Robot robot, int procedureTime) {
        robot.setHappiness(Math.min(100, robot.getHappiness() + HAPPINESS_ADD));
        robot.setEnergy(Math.min(100, robot.getEnergy() + ENERGY_ADD));
        super.doService(robot, procedureTime);
    }
}
