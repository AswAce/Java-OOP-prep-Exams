package spaceStation.core;

import spaceStation.common.ConstantMessages;
import spaceStation.common.ExceptionMessages;
import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.mission.MissionImpl;
import spaceStation.models.planets.PlanetImpl;
import spaceStation.repositories.AstronautRepository;
import spaceStation.repositories.PlanetRepository;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class ControllerImpl implements Controller {
    private AstronautRepository astronautRepository;
    private PlanetRepository planetRepository;
    private MissionImpl mission;
    private int exploredPlanet;

    public ControllerImpl() {
        this.astronautRepository = new AstronautRepository();
        this.planetRepository = new PlanetRepository();
        this.mission = new MissionImpl();
        this.exploredPlanet = 0;
    }

    @Override
    public String addAstronaut(String type, String astronautName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        try {
            Class<?> aClass = Class.forName("spaceStation.models.astronauts." + type);
            this.astronautRepository.add((Astronaut) (aClass.getConstructor(String.class).newInstance(astronautName)));
            return String.format(ConstantMessages.ASTRONAUT_ADDED, type, astronautName);
        } catch (NoSuchElementException | ClassNotFoundException e) {
            throw new IllegalArgumentException(ExceptionMessages.ASTRONAUT_INVALID_TYPE);
        }

    }

    @Override
    public String addPlanet(String planetName, String... items) {
        planetRepository.add(new PlanetImpl(planetName));
        planetRepository.findByName(planetName).getItems().addAll(Arrays.asList(items));

        return String.format(ConstantMessages.PLANET_ADDED, planetName);
    }

    @Override
    public String retireAstronaut(String astronautName) {
        for (Astronaut astronaut : this.astronautRepository.getModels()) {
            if (astronaut.getName().equals(astronautName)) {
                this.astronautRepository.remove(astronaut);
                return String.format(ConstantMessages.ASTRONAUT_RETIRED, astronautName);
            }
        }
        throw new IllegalArgumentException(String.format
                (ExceptionMessages.ASTRONAUT_DOES_NOT_EXIST, astronautName));
    }

    @Override
    public String explorePlanet(String planetName) {
        ArrayList<Astronaut> suitableForMission = new ArrayList<>();
        int deadAstronaut = 0;
        for (Astronaut astronaut : this.astronautRepository.getModels()) {
            if (astronaut.getOxygen() >= 60) {
                suitableForMission.add(astronaut);
            }
        }
        if (suitableForMission.size() == 0) {
            throw new IllegalArgumentException(ExceptionMessages.PLANET_ASTRONAUTS_DOES_NOT_EXISTS);
        }

        mission.explore(planetRepository.findByName(planetName), suitableForMission);

        for (Astronaut astronaut : suitableForMission) {
            if (astronaut.getOxygen() <= 0) {
                deadAstronaut++;
            }
        }
        exploredPlanet++;
        return String.format(ConstantMessages.PLANET_EXPLORED, planetName, deadAstronaut);
    }

    @Override
    public String report() {
        StringBuilder builder = new StringBuilder(String.format
                (ConstantMessages.REPORT_PLANET_EXPLORED, this.exploredPlanet)).append(System.lineSeparator());
        builder.append(ConstantMessages.REPORT_ASTRONAUT_INFO).append(System.lineSeparator());
        for (Astronaut astronaut : this.astronautRepository.getModels()) {
            builder.append(astronaut.toString()).append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
