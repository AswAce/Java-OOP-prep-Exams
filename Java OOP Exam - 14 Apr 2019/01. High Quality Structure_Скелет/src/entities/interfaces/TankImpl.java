package entities.interfaces;

public class TankImpl extends BaseMachine implements Tank {
    private static final double HEALTH_POINT_TANK = 100;
    private boolean defenceMode;
    private static final double ATTACK_POINT_MODIFIER = 40;
    private static final double DEFFENCE_POINT_MODIFIER = 30;

    public TankImpl(String name, double attackPoints, double defensePoints) {
        super(name, attackPoints - ATTACK_POINT_MODIFIER, defensePoints + DEFFENCE_POINT_MODIFIER, HEALTH_POINT_TANK);
        this.defenceMode = true;
    }

    @Override
    public boolean getDefenseMode() {
        return this.defenceMode;
    }

    @Override
    public void toggleDefenseMode() {

        this.setDefenceMode(!this.getDefenseMode());
        this.changeAttackDefence(this.getDefenseMode());

    }

    public void setDefenceMode(boolean defenceMode) {
        this.defenceMode = defenceMode;
    }

    @Override
    public void setHealthPoints(double healthPoints) {
        super.setHealthPoints(healthPoints);


    }


    @Override
    public void attack(String target) {
        super.attack(target);
    }

    private void changeAttackDefence(boolean mode) {
        if (mode) {
            super.setAttackPoints(super.getAttackPoints() - ATTACK_POINT_MODIFIER);
            super.setDefensePoints(super.getDefensePoints() + DEFFENCE_POINT_MODIFIER);
        } else {
            super.setAttackPoints(super.getAttackPoints() + ATTACK_POINT_MODIFIER);
            super.setDefensePoints(super.getDefensePoints() - DEFFENCE_POINT_MODIFIER);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("- " + super.getName()).
                append(System.lineSeparator()).
                append(" *Type: Tank\n ");

        stringBuilder.append(super.toString());
        stringBuilder.append(System.lineSeparator());
        if (this.getDefenseMode()) {
            stringBuilder.append(" *Defense Mode(ON)\n");
        } else {
            stringBuilder.append(" *Defense Mode(OFF)\n");
        }

        return stringBuilder.toString().trim();
    }
}

