package models.cards;

public class Trap extends Base {
    private static final int defaultDamagePoints = 120;
    private static final int defaultHealthPoints = 5;

    public Trap(String name) {
        super(name, defaultDamagePoints, defaultHealthPoints);
    }
}
