package spaceStation.models.astronauts;

public class Biologist extends BaseAstronaut {
    private static final double OXYGEN_LEVEL = 70;
    private static final double OXYGEN_TO_BREAD = 5;

    public Biologist(String name) {
        super(name, OXYGEN_LEVEL);
    }

    @Override
    public void breath() {
        if (super.getOxygen() - OXYGEN_TO_BREAD <= 0) {
            super.setOxygen(0);
        } else {
            super.setOxygen(this.getOxygen() - OXYGEN_TO_BREAD);
        }
    }
    }

