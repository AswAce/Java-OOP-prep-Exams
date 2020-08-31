package models.procedures.interfaces;

import common.ExceptionMessages;
import models.robots.interfaces.Robot;

public class Repair extends BaseProcedure {
    private static final int REMOVED_HAPPINESS = 5;

    @Override
    public void doService(Robot robot, int procedureTime) {
        if (robot.isRepaired()) {
            throw new IllegalArgumentException(String.format
                    (ExceptionMessages.ALREADY_REPAIRED, robot.getName()));
        }
        robot.setRepaired(true);
        robot.setHappiness(Math.max(0, robot.getHappiness() - REMOVED_HAPPINESS));
        super.doService(robot, procedureTime);

    }
}
