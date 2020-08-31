package entities.interfaces;

public class FighterImpl extends BaseMachine implements Fighter {
    private static final double HEALTH_POINT_FIGHTER = 200;
    private boolean aggressiveMode;
    private static final double ATTACK_POINT_MODIFIER = 50;
    private static final double DEFFENCE_POINT_MODIFIER = 25;

    public FighterImpl(String name, double attackPoints,
                       double defensePoints) {
        super(name, attackPoints + ATTACK_POINT_MODIFIER, defensePoints - DEFFENCE_POINT_MODIFIER, HEALTH_POINT_FIGHTER);
        this.aggressiveMode = true;
    }

    public void setAggressiveMode(boolean aggressiveMode) {
        this.aggressiveMode = aggressiveMode;
    }

    @Override
    public void setHealthPoints(double healthPoints) {
        super.setHealthPoints(healthPoints);
    }

    @Override
    public void attack(String target) {
        super.attack(target);
    }

    @Override
    public boolean getAggressiveMode() {
        return this.aggressiveMode;
    }

    @Override
    public void toggleAggressiveMode() {
        this.setAggressiveMode(!this.getAggressiveMode());
        changeAttackDefence(this.getAggressiveMode());


    }

    private void changeAttackDefence(boolean mode) {
        if (mode) {
            super.setAttackPoints(super.getAttackPoints() + ATTACK_POINT_MODIFIER);
            super.setDefensePoints(super.getDefensePoints() - DEFFENCE_POINT_MODIFIER);
        } else {
            super.setAttackPoints(super.getAttackPoints() - ATTACK_POINT_MODIFIER);
            super.setDefensePoints(super.getDefensePoints() + DEFFENCE_POINT_MODIFIER);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("- " + super.getName()).
                append(System.lineSeparator()).
                append(" *Type: Fighter\n ");

        stringBuilder.append(super.toString());
        stringBuilder.append(System.lineSeparator());
        if (this.getAggressiveMode()) {
            stringBuilder.append(" *Aggressive Mode(ON)\n");
        } else {
            stringBuilder.append(" *Aggressive Mode(OFF)\n");
        }

        return stringBuilder.toString().trim();
    }
}
