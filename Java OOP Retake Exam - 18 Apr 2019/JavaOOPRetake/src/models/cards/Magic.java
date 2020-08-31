package models.cards;

public class Magic extends Base {
    private static final int defaultDamagePoints = 5;
    private static final int defaultHealthPoints = 80;

    public Magic(String name) {
        super(name, defaultDamagePoints, defaultHealthPoints);
    }
}
