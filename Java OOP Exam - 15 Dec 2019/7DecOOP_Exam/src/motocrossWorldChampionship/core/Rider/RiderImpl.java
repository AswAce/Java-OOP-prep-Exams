package motocrossWorldChampionship.core.Rider;

import motocrossWorldChampionship.common.ExceptionMessages;
import motocrossWorldChampionship.entities.interfaces.Motorcycle;
import motocrossWorldChampionship.entities.interfaces.Rider;

public class RiderImpl implements Rider {

//    numberOfWins – int
//    canParticipate – boolean (default behaviour is false).
//    A rider can participate in a race, ONLY if he has motorcycle (motorcycle is not null)

    private String name;
    private Motorcycle motorcycle;
    private int numberOfWins;
    private boolean canParticipate;

    public RiderImpl(String name) {
      setName(name);
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty() || name.length() < 4) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_NAME, name, 4));
        }
        this.name = name;
    }

    private void setCanParticipate(boolean canParticipate) {
        this.canParticipate = canParticipate;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Motorcycle getMotorcycle() {
        return this.motorcycle;
    }

    @Override
    public int getNumberOfWins() {
        return this.numberOfWins;
    }

    @Override
    public void addMotorcycle(Motorcycle motorcycle) {
        if (motorcycle == null) {
            throw new NullPointerException("Motorcycle cannot be null.");
        }
        this.motorcycle = motorcycle;
        this.setCanParticipate(true);

    }

    @Override
    public void winRace() {
        this.numberOfWins++;
    }

    @Override
    public boolean getCanParticipate() {
        return this.canParticipate;
    }
}
