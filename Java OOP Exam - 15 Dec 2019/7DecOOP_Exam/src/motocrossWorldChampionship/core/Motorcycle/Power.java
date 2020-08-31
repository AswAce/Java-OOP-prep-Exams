package motocrossWorldChampionship.core.Motorcycle;

import motocrossWorldChampionship.common.ExceptionMessages;


public class Power extends MotorcycleImpl {
    private static final int MINIMUM_HORSEPOWER = 70;
    private static final int MAXIMUM_HORSEPOWER = 100;
    private static final int CUBIC_CM = 450;


    public Power(String model, int horsePower) {
        super(model, horsePower, CUBIC_CM);

    }

    @Override
    protected void setHorsePower(int horsePower) {
        if (horsePower < MINIMUM_HORSEPOWER || horsePower > MAXIMUM_HORSEPOWER) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_HORSE_POWER, horsePower));
        }
        super.setHorsePower(horsePower);
    }


}
