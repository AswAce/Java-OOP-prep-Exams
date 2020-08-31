package spaceStation.repositories;

import spaceStation.models.planets.Planet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PlanetRepository implements Repository<Planet> {

    List<Planet> planets;

    public PlanetRepository() {
        this.planets = new ArrayList<>();
    }

    @Override
    public Collection<Planet> getModels() {
        return this.planets;
    }

    @Override
    public void add(Planet model) {
        if (!this.planets.contains(model)) {
            this.planets.add(model);
        }

    }

    @Override
    public boolean remove(Planet model) {
        return this.planets.remove(model);
    }

    @Override
    public Planet findByName(String name) {
        for (Planet planet : this.planets) {
            if (planet.getName().equals(name)) {
                return planet;
            }
        }
        return null;
    }
}
