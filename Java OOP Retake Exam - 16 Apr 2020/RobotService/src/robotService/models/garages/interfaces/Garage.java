package models.garages.interfaces;

import models.robots.interfaces.Robot;

import java.util.Map;

public interface Garage {

    Map<String, Robot> getRobots();

    void manufacture(Robot robot);

    void sell(String robotName, String ownerName);
}
