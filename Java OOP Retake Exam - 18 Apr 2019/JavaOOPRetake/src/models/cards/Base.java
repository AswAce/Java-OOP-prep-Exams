package models.cards;

import models.cards.interfaces.Card;

public abstract class Base implements Card {
    private String name;
    private int damagePoints;
    private int healthPoints;

    public Base(String name, int damagePoints, int healthPoints) {
        setName(name);
        setDamagePoints(damagePoints);
        setHealthPoints(healthPoints);
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Card's name cannot be null or an empty string.");
        }
        this.name = name;
    }

    public void setHealthPoints(int healthPoints) {
        if (healthPoints < 0) {
            throw new IllegalArgumentException("Card's HP cannot be less than zero.");
        }
        this.healthPoints = healthPoints;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getDamagePoints() {
        return this.damagePoints;
    }

    @Override
    public void setDamagePoints(int damagePoints) {
        if (damagePoints < 0) {
            throw new IllegalArgumentException("Card's damage points cannot be less than zero.");
        }
        this.damagePoints = damagePoints;
    }

    @Override
    public int getHealthPoints() {
        return this.healthPoints;
    }

    @Override
    public String toString() {
        return "Card: " + this.name + " - Damage: " + this.damagePoints;
    }
}
