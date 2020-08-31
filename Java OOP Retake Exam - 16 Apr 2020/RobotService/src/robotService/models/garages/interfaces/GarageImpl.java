package models.garages.interfaces;

import common.ExceptionMessages;
import models.robots.interfaces.Robot;

import java.util.LinkedHashMap;
import java.util.Map;

public class GarageImpl implements Garage {

    private static final int CAPACITY = 10;
    private Map<String, Robot> robots;

    public GarageImpl() {
        this.robots = new LinkedHashMap<>();
    }

    @Override
    public Map<String, Robot> getRobots() {
        return this.robots;
    }

    @Override
    public void manufacture(Robot robot) {
        if (CAPACITY - this.robots.size() <= 0) {
            throw new IllegalStateException(ExceptionMessages.NOT_ENOUGH_CAPACITY);
        }
        if (robots.containsKey(robot.getName())) {
            throw new IllegalArgumentException(
                    String.format(ExceptionMessages.EXISTING_ROBOT, robot.getName()));
        }
        this.robots.put(robot.getName(), robot);

    }

    @Override
    public void sell(String robotName, String ownerName) {
        if (!this.robots.containsKey(robotName)) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NON_EXISTING_ROBOT, robotName));
        }
        Robot boughtRobot = this.robots.remove(robotName);
        boughtRobot.setOwner(ownerName);
        boughtRobot.setBought(true);
    }
}
