package models.procedures;

import models.robots.interfaces.Robot;

public class Charge extends BaseProcedure {
    private static final int HAPPINESS_ADD = 12;
    private static final int ENERGY_ADD = 10;


    @Override
    public void doService(Robot robot, int procedureTime) {
        super.doService(robot, procedureTime);
        robot.setHappiness(robot.getHappiness() + HAPPINESS_ADD);
        robot.setEnergy(robot.getEnergy() + ENERGY_ADD);

    }
}
