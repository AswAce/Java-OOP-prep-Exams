package entities.interfaces;
import java.util.ArrayList;
import java.util.List;

public class PilotImpl implements Pilot {
    private String name;
    private List<Machine> machines;

    public PilotImpl(String name) {
        setName(name);
        this.machines = new ArrayList<>();
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Pilot name cannot be null or empty string.");
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void addMachine(Machine machine) {
        if (machine == null) {
            throw new NullPointerException("Null machine cannot be added to the pilot.");
        }
        this.machines.add(machine);
    }

    @Override
    public List<Machine> getMachines() {
        return this.machines;
    }

    @Override
    public String report() {
        StringBuilder stringBuilder = new StringBuilder(
                this.getName() + " - " + this.machines.size() + " machines").append(System.lineSeparator());
        for (Machine machine : machines) {
            stringBuilder.append(machine.toString()).append(System.lineSeparator());

        }
        return stringBuilder.toString().trim();
    }
}
