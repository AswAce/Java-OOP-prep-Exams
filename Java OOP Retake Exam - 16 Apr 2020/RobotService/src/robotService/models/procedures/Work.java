package models.procedures;

import models.robots.interfaces.Robot;

public class Work extends BaseProcedure {
    private static final int HAPPINESS_ADD = 12;
    private static final int ENERGY_REMOVED = 6;



    @Override
    public void doService(Robot robot, int procedureTime) {
        super.doService(robot, procedureTime);
        robot.setHappiness(robot.getHappiness() + HAPPINESS_ADD);
        robot.setEnergy(robot.getEnergy() - ENERGY_REMOVED);

    }

}
