package spaceStation.models.astronauts;

public class Meteorologist extends BaseAstronaut {
    private static final double OXYGEN_LEVEL = 90;
    public Meteorologist(String name) {
        super(name, OXYGEN_LEVEL);
    }

}
