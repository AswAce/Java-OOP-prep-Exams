package models.procedures;

import common.ExceptionMessages;
import models.robots.interfaces.Robot;

public class Repair extends BaseProcedure {
    private static final int REMOVED_HAPPINESS = 5;

    @Override
    public void doService(Robot robot, int procedureTime) {
        super.doService(robot, procedureTime);

        if (robot.isRepaired()) {
            throw new IllegalArgumentException(String.format
                    (ExceptionMessages.ALREADY_REPAIRED, robot.getName()));
        }
        robot.setHappiness(robot.getHappiness() - REMOVED_HAPPINESS);
        robot.setRepaired(true);



    }
}
