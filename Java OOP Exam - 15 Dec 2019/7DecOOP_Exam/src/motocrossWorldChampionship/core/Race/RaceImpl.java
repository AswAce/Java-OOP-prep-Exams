package motocrossWorldChampionship.core.Race;

import motocrossWorldChampionship.common.ExceptionMessages;
import motocrossWorldChampionship.entities.interfaces.Race;
import motocrossWorldChampionship.entities.interfaces.Rider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RaceImpl implements Race {


    private String name;
    private int laps;
    List<Rider> riders;

    public RaceImpl(String name, int laps) {
        setName(name);
        setLaps(laps);
        this.riders = new ArrayList<>();
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty() || name.length() < 5) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_NAME, name, 5));
        }
        this.name = name;
    }

    public void setLaps(int laps) {
        if (laps < 1) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_NUMBER_OF_LAPS, laps));
        }
        this.laps = laps;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getLaps() {
        return this.laps;
    }

    @Override
    public Collection<Rider> getRiders() {
        return this.riders;
    }

    @Override
    public void addRider(Rider rider) {
        if (rider == null) {
            throw new NullPointerException("Rider cannot be null.");
        }
        if (!rider.getCanParticipate()) {
            throw new IllegalArgumentException
                    (String.format(ExceptionMessages.RIDER_NOT_PARTICIPATE, rider.getName()));
        }
        if (this.getRiders().contains(rider)) {
            throw new IllegalArgumentException
                    (String.format(ExceptionMessages.RIDER_ALREADY_ADDED, rider.getName(), this.getName()));
        }
        this.riders.add(rider);
    }
}
