package models.players;

import common.ExceptionMessages;
import models.guns.Gun;

public abstract class PlayerImpl implements Player {
    private String userName;
    private int health;
    private int armor;
    private Gun gun;


    protected PlayerImpl(String userName, int health, int armor, Gun gun) {
        setUserName(userName);
        setHealth(health);
        setArmor(armor);
        setGun(gun);


    }

    private void setUserName(String userName) {
        if (userName == null || userName.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.INVALID_PLAYER_NAME);
        }
        this.userName = userName;
    }

    private void setHealth(int health) {
        if (health < 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_HEALTH);
        }
        this.health = health;
    }

    private void setArmor(int armor) {
        if (armor < 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_ARMOR);
        }
        this.armor = armor;
    }

    private void setGun(Gun gun) {
        if (gun == null) {
            throw new NullPointerException(ExceptionMessages.INVALID_GUN);
        }
        this.gun = gun;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public int getArmor() {
        return this.armor;
    }

    @Override
    public Gun getGun() {
        return this.gun;
    }

    @Override
    public boolean isAlive() {
        return this.getHealth() > 0;
    }

    @Override
    public void takeDamage(int points) {
        int leftDmg = points - this.getArmor();
        this.setArmor(Math.max(0, this.getArmor() - points));
        if (this.getArmor() == 0) {
            this.setHealth(Math.max(0, this.getHealth() - leftDmg));
        }
    }

    @Override
    public String toString() {

        String play = this.getClass().getSimpleName() +
                ": " +
                this.getUsername() + System.lineSeparator() +
                "--Health: " + this.getHealth() + System.lineSeparator() +
                "--Armor: " + this.getArmor() + System.lineSeparator() +
                "--Gun: " + this.getGun().getName() + System.lineSeparator();

        return play.trim();
    }
}
