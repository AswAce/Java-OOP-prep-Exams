package models.procedures.interfaces;

import models.robots.interfaces.Robot;

public class Work extends BaseProcedure {
    private static final int HAPPINESS_ADD = 12;
    private static final int ENERGY_REMOVED = 6;

    @Override
    public void doService(Robot robot, int procedureTime) {
        robot.setHappiness(Math.min(100, robot.getHappiness() + HAPPINESS_ADD));
        robot.setEnergy(Math.max(0, robot.getEnergy() - ENERGY_REMOVED));
        super.doService(robot, procedureTime);
    }

}
