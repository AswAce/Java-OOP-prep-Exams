package spaceStation.models.mission;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.planets.Planet;
import spaceStation.repositories.AstronautRepository;
import spaceStation.repositories.PlanetRepository;

import java.util.ArrayDeque;
import java.util.Collection;

public class MissionImpl implements Mission {


    public MissionImpl() {
    }

    @Override
    public void explore(Planet planet, Collection<Astronaut> astronauts) {

        ArrayDeque<String> items = (ArrayDeque<String>) planet.getItems();

        for (Astronaut astronaut : astronauts) {
            while (astronaut.canBreath() && !items.isEmpty()) {
                astronaut.getBag().getItems().add(items.poll());
                astronaut.breath();
            }
        }
    }
}
