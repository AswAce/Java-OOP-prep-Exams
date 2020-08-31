package santasWorkshop.models;

public class Sleepy extends  BaseDwarf {
    private static final int ENERGY = 50;
    private static  final int ADDITIONAL_DEGREASE_ENERGY=5;

    public Sleepy(String name) {
        super(name, ENERGY);
    }

    @Override
    public void work() {
        super.setEnergy(Math.max(this.getEnergy() - 10+ADDITIONAL_DEGREASE_ENERGY, 0));

    }
}
