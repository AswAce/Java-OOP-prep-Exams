package models.players;

import models.guns.Gun;

public class CounterTerrorist extends PlayerImpl{
    public CounterTerrorist(String userName, int health, int armor, Gun gun) {
        super(userName, health, armor, gun);
    }
}
