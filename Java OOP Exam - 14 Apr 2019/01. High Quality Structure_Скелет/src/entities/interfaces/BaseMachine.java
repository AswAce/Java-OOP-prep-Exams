package entities.interfaces;
import java.util.ArrayList;

import java.util.List;

public abstract class BaseMachine implements Machine {
    private String name;
    private Pilot pilot;
    private double attackPoints;
    private double defensePoints;
    private double healthPoints;
    private List<String> targets;

    protected BaseMachine(String name, double attackPoints, double defensePoints, double healthPoints) {
        setName(name);
        this.attackPoints = attackPoints;
        this.defensePoints = defensePoints;
        this.healthPoints = healthPoints;
        this.targets = new ArrayList<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Machine name cannot be null or empty.");
        }
        this.name = name;

    }

    @Override
    public Pilot getPilot() {
        return this.pilot;
    }

    protected void setAttackPoints(double attackPoints) {
        this.attackPoints = attackPoints;
    }

    protected void setDefensePoints(double defensePoints) {
        this.defensePoints = defensePoints;
    }

    @Override
    public void setPilot(Pilot pilot) {
        if (pilot == null || name.trim().isEmpty()) {
            throw new NullPointerException("Pilot cannot be null.");
        }
        this.pilot = pilot;
    }

    @Override
    public double getHealthPoints() {
        return this.healthPoints;
    }

    @Override
    public double getAttackPoints() {
        return this.attackPoints;
    }

    @Override
    public double getDefensePoints() {
        return this.defensePoints;
    }

    @Override
    public List<String> getTargets() {
        return targets;
    }

    @Override
    public void attack(String target) {
        if (target == null || target.trim().isEmpty()) {
            throw new IllegalArgumentException("Attack target cannot be null or empty string.");
        }
        this.targets.add(target);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format(" *Health: %.2f\n", this.getHealthPoints()));
        stringBuilder.append(String.format(" *Attack: %.2f\n", this.getAttackPoints()));
        stringBuilder.append(String.format(" *Defense: %.2f\n", this.getDefensePoints()));
        stringBuilder.append(" *Targets: ");

        if (targets.isEmpty()) {
            stringBuilder.append("None");
            return stringBuilder.toString().trim();
        } else {
            for (String target : targets) {
                stringBuilder.append(target).append(", ");
            }
            stringBuilder.append(System.lineSeparator());
            return stringBuilder.toString().
                    substring(0, stringBuilder.toString().length() - 3).trim();
        }

    }

    @Override
    public void setHealthPoints(double healthPoints) {
        this.healthPoints = healthPoints;
    }
}

