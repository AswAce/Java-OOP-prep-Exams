package santasWorkshop.models;

public class PresentImpl implements Present {
    private static final int ENERGY_DECREASE = 10;
    private String name;
    private int energyRequired;


    public PresentImpl(String name, int energyRequired) {
        this.name = name;
        this.energyRequired = energyRequired;
    }

    private void setEnergyRequired(int energyRequired) {
        this.energyRequired = energyRequired;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getEnergyRequired() {
        return this.energyRequired;
    }

    @Override
    public boolean isDone() {
        return this.getEnergyRequired() <= 0;
    }

    @Override
    public void getCrafted() {
        this.setEnergyRequired(Math.max(this.getEnergyRequired() - ENERGY_DECREASE, 0));
    }
}
