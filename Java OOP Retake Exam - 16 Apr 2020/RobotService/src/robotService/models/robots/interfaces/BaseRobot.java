package models.robots.interfaces;

import common.ExceptionMessages;

public abstract class BaseRobot implements Robot {
    private String name;
    private int happiness;
    private int energy;
    private int productionTime;
    private String owner;
    //    "Service"
    private boolean isBought;
    private boolean isRepaired;

    public BaseRobot(String name, int energy, int happiness, int productionTime) {
        this.name = name;
        setHappiness(happiness);
        setEnergy(energy);
        setProcedureTime(productionTime);
        setOwner("Service");
        setBought(false);
        setRepaired(false);

    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getHappiness() {
        return this.happiness;
    }

    @Override
    public void setHappiness(int happiness) {
        if (happiness < 0 || happiness > 100) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_HAPPINESS);
        }
        this.happiness = happiness;
    }

    @Override
    public int getEnergy() {
        return this.energy;
    }

    @Override
    public void setEnergy(int energy) {
        if (energy < 0 || energy > 100) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_ENERGY);
        }
        this.energy = energy;
    }

    @Override
    public int getProcedureTime() {
        return this.productionTime;
    }

    @Override
    public void setProcedureTime(int procedureTime) {
        this.productionTime = procedureTime;
    }

    @Override
    public void setOwner(String owner) {
        this.owner = owner;

    }

    @Override
    public void setBought(boolean bought) {
        this.isBought = bought;
    }

    @Override
    public boolean isRepaired() {
        return this.isRepaired;
    }

    @Override
    public void setRepaired(boolean repaired) {
        this.isRepaired = repaired;
    }


    @Override
    public String toString() {

        return "Robot type: " + this.getClass().getSimpleName()
                + " - " +
                "" + this.getName()
                + " - Happiness: "
                + this.getHappiness()
                + " - Energy: " + this.energy + "\n";
    }
}
