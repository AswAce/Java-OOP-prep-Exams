package spaceStation.models.astronauts;

import spaceStation.common.ConstantMessages;
import spaceStation.common.ExceptionMessages;
import spaceStation.models.bags.Backpack;
import spaceStation.models.bags.Bag;

public abstract class BaseAstronaut implements Astronaut {
    private static final double OXYGEN_TO_BREAD = 10;

    private String name;
    private double oxygen;
    private Bag bag;

    protected BaseAstronaut(String name, double oxygen) {
        setName(name);
        setOxygen(oxygen);
        this.bag = new Backpack();
    }

    protected void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.ASTRONAUT_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    protected void setOxygen(double oxygen) {
        if (oxygen < 0) {
            throw new IllegalArgumentException(ExceptionMessages.ASTRONAUT_OXYGEN_LESS_THAN_ZERO);
        }
        this.oxygen = oxygen;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getOxygen() {
        return this.oxygen;
    }

    @Override
    public boolean canBreath() {
        return this.getOxygen() > 0;
    }

    @Override
    public Bag getBag() {
        return this.bag;
    }

    @Override
    public void breath() {
        if (this.getOxygen() - OXYGEN_TO_BREAD <= 0) {
            this.setOxygen(0);
        } else {
            this.setOxygen(this.getOxygen() - OXYGEN_TO_BREAD);
        }
    }

    @Override
    public String toString() {

        String builder = System.lineSeparator()+String.format(ConstantMessages.REPORT_ASTRONAUT_NAME, this.getName()) + System.lineSeparator() +
                String.format(ConstantMessages.REPORT_ASTRONAUT_OXYGEN, this.getOxygen()) + System.lineSeparator() +
                this.bag.toString() + System.lineSeparator();
        return builder.trim();
    }
}
