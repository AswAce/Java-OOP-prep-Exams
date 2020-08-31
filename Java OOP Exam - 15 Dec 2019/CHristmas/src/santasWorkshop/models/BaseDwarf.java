package santasWorkshop.models;

import santasWorkshop.common.ExceptionMessages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class BaseDwarf implements Dwarf {
    private static final int REGULAR_ENERGY_DECREASE = 10;
    private String name;
    private int energy;
    List<Instrument> instruments;

    protected BaseDwarf(String name, int energy) {
        setName(name);
        setEnergy(energy);
        this.instruments = new ArrayList<>();
    }

    protected void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.DWARF_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    protected void setEnergy(int energy) {
        if (energy < 0) {
            throw new
                    IllegalArgumentException(ExceptionMessages.DWARF_ENERGY_LESS_THAN_ZERO);
        }
        this.energy = energy;
    }

    @Override
    public void work() {
        this.setEnergy(Math.max(this.getEnergy() - REGULAR_ENERGY_DECREASE, 0));

    }

    @Override
    public void addInstrument(Instrument instrument) {
        this.instruments.add(instrument);
    }

    @Override
    public boolean canWork() {
        return this.getEnergy() > 0;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getEnergy() {
        return this.energy;
    }

    @Override
    public Collection<Instrument> getInstruments() {
        return this.instruments;
    }

    @Override
    public String toString() {
        int length = this.getInstruments().stream().filter(e -> e.getPower() > 0).toArray().length;
        StringBuilder builder = new StringBuilder("Name: " + this.getName()).append(System.lineSeparator());
        builder.append("Energy: ").append(this.getEnergy()).append(System.lineSeparator());
        builder.append("Instruments ").append(length).append(" not broken left").append(System.lineSeparator());

        return builder.toString();
    }
}
