package models.procedures.interfaces;

import common.ExceptionMessages;
import models.robots.interfaces.Robot;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseProcedure implements Procedure {
    private List<Robot> robots;

    public BaseProcedure() {
        this.robots = new ArrayList<>();
    }

    @Override
    public String history() {
        StringBuilder procedureHistory = new StringBuilder(this.getClass().getSimpleName()).append(System.lineSeparator());
        for (Robot robot : robots) {
            procedureHistory.append(robots.toString()).append(System.lineSeparator());
        }
        return procedureHistory.toString().trim();
    }

    @Override
    public void doService(Robot robot, int procedureTime) {
        if (robot.getProcedureTime() < procedureTime) {
            throw new IllegalArgumentException(ExceptionMessages.INSUFFICIENT_PROCEDURE_TIME);
        }
        this.robots.add(robot);
        robot.setProcedureTime(Math.max(0, robot.getProcedureTime() - procedureTime));

    }
}
