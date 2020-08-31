package spaceStation.models.astronauts;

public class Geodesist extends BaseAstronaut {

    private static final double OXYGEN_LEVEL = 50;
    public Geodesist(String name) {
        super(name, OXYGEN_LEVEL);
    }
}
