package santasWorkshop.models;

import santasWorkshop.common.ExceptionMessages;


public class InstrumentImpl implements Instrument {
    private static final int POWER_DECREASE = 10;
    private int power;

    public InstrumentImpl(int power) {
        this.setPower(power);
    }

    public void setPower(int power) {
        if (power < 0) {
            throw new IllegalArgumentException(ExceptionMessages.INSTRUMENT_POWER_LESS_THAN_ZERO);

        }
        this.power = power;
    }

    @Override
    public int getPower() {
        return this.power;
    }


    @Override
    public void use() {
        this.setPower(Math.max(this.getPower() - POWER_DECREASE, 0));
    }

    @Override
    public boolean isBroken() {
        return this.getPower()<=0;
    }
}
