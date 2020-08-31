package motocrossWorldChampionship.core.Motorcycle;

import motocrossWorldChampionship.common.ExceptionMessages;
import motocrossWorldChampionship.core.Motorcycle.MotorcycleImpl;

public class Speed extends MotorcycleImpl {

    private static final int MINIMUM_HORSEPOWER = 50;
    private static final int MAXIMUM_HORSEPOWER = 69;
    private static final int CUBIC_CM = 125;


    public Speed(String model, int horsePower) {
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
