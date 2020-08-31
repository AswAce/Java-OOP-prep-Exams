package models.procedures;

import common.ExceptionMessages;
import models.procedures.interfaces.Procedure;
import models.robots.interfaces.Robot;

import java.util.ArrayList;
import java.util.Collection;

import static common.ExceptionMessages.INSUFFICIENT_PROCEDURE_TIME;

public abstract class BaseProcedure implements Procedure {
    protected Collection<Robot> robots;

    protected BaseProcedure() {
        this.robots = new ArrayList<>();
    }

    @Override
    public String history() {
        StringBuilder procedureHistory = new StringBuilder(this.getClass().getSimpleName()).append(System.lineSeparator());
        for (Robot robot : robots) {
            procedureHistory.append(robot.toString()).append(System.lineSeparator());
        }
        return procedureHistory.toString().trim();
    }

    @Override
    public void doService(Robot robot, int procedureTime) {
        if (robot.getProcedureTime() < procedureTime) {
            throw new IllegalArgumentException(INSUFFICIENT_PROCEDURE_TIME);
        }
        robot.setProcedureTime(robot.getProcedureTime() - procedureTime);
        this.robots.add(robot);


    }
}
