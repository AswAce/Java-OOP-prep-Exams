package motocrossWorldChampionship.core;

import motocrossWorldChampionship.common.ExceptionMessages;
import motocrossWorldChampionship.common.OutputMessages;
import motocrossWorldChampionship.core.Race.RaceImpl;
import motocrossWorldChampionship.core.Rider.RiderImpl;
import motocrossWorldChampionship.core.interfaces.ChampionshipController;
import motocrossWorldChampionship.entities.interfaces.Motorcycle;
import motocrossWorldChampionship.entities.interfaces.Race;
import motocrossWorldChampionship.entities.interfaces.Rider;
import motocrossWorldChampionship.repositories.interfaces.MotorRepository;
import motocrossWorldChampionship.repositories.interfaces.RaceRepository;
import motocrossWorldChampionship.repositories.interfaces.RacerRepository;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ChampionshipControllerImpl implements ChampionshipController {

    private RaceRepository raceRepository;
    private RacerRepository racerRepository;
    private MotorRepository motorRepository;

    public ChampionshipControllerImpl() {
        this.raceRepository = new RaceRepository();
        this.racerRepository = new RacerRepository();
        this.motorRepository = new MotorRepository();
    }

    @Override
    public String createRider(String riderName) {
        if (this.racerRepository.getByName(riderName) == null) {
            this.racerRepository.add(new RiderImpl(riderName));
            return String.format(OutputMessages.RIDER_CREATED, riderName);
        }
        throw new IllegalArgumentException(String.format(ExceptionMessages.RIDER_EXISTS, riderName));

    }

    @Override
    public String createMotorcycle(String type, String model, int horsePower) {
        try {
            Class<?> newMotor = Class.forName("motocrossWorldChampionship.core.Motorcycle." + type);
            Motorcycle newMotorType = (Motorcycle) newMotor.getConstructor(String.class, int.class).newInstance(model, horsePower);
            if (this.motorRepository.getByName(model) == null) {
                this.motorRepository.add(newMotorType);
                return String.format(OutputMessages.MOTORCYCLE_CREATED, type + "Motorcycle", model);

            }
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            //empty
        }
        throw new IllegalArgumentException(String.format(ExceptionMessages.MOTORCYCLE_EXISTS, model));
    }

    @Override
    public String addMotorcycleToRider(String riderName, String motorcycleModel) {

        if (this.racerRepository.getByName(riderName) == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RIDER_NOT_FOUND, riderName));
        }
        if (this.motorRepository.getByName(motorcycleModel) == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.MOTORCYCLE_NOT_FOUND, motorcycleModel));
        }
        this.racerRepository.getByName(riderName).addMotorcycle(this.motorRepository.getByName(motorcycleModel));
        return String.format(OutputMessages.MOTORCYCLE_ADDED, riderName, motorcycleModel);
    }


    @Override
    public String addRiderToRace(String raceName, String riderName) {
        if (this.racerRepository.getByName(riderName) == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RIDER_NOT_FOUND, riderName));
        }
        if (this.raceRepository.getByName(raceName) == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_NOT_FOUND, raceName));
        }

        this.raceRepository.getByName(raceName).addRider(this.racerRepository.getByName(riderName));

        return String.format(OutputMessages.RIDER_ADDED, riderName, raceName);
    }

    @Override
    public String startRace(String raceName) {
        if (this.raceRepository.getByName(raceName) == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_NOT_FOUND, raceName));
        }
        if (this.raceRepository.getByName(raceName).getRiders().size() < 3) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_INVALID, raceName, 3));
        }
        int raceLaps = this.raceRepository.getByName(raceName).getLaps();
        List<Rider> bestThreeRiders = this.raceRepository.getByName(raceName).getRiders().stream().sorted((e1, e2) -> {
            double getFirst = e1.getMotorcycle().calculateRacePoints(raceLaps);
            double getSecond = e2.getMotorcycle().calculateRacePoints(raceLaps);
            return Double.compare(getSecond, getFirst);
        }).limit(3).collect(Collectors.toList());

        bestThreeRiders.get(0).winRace();
        StringBuilder builder = new StringBuilder
                (String.format(OutputMessages.RIDER_FIRST_POSITION,
                        bestThreeRiders.get(0).getName(), raceName)).append(System.lineSeparator());
        builder.append(String.format(OutputMessages.RIDER_SECOND_POSITION,
                bestThreeRiders.get(1).getName(), raceName)).append(System.lineSeparator());
        builder.append(String.format(OutputMessages.RIDER_THIRD_POSITION,
                bestThreeRiders.get(2).getName(), raceName)).append(System.lineSeparator());
        this.raceRepository.remove(this.raceRepository.getByName(raceName));
        return builder.toString();
    }

    @Override
    public String createRace(String name, int laps) {
        if (this.raceRepository.getByName(name) != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_EXISTS, name));
        }
        this.raceRepository.add(new RaceImpl(name, laps));
        return String.format(OutputMessages.RACE_CREATED, name);
    }
}
