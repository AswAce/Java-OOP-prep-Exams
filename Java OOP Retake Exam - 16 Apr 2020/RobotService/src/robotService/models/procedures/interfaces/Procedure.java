package models.procedures.interfaces;


import models.robots.interfaces.Robot;

public interface Procedure {
    String history();

    void doService(Robot robot, int procedureTime);
}
