package spaceStation.core;

import java.lang.reflect.InvocationTargetException;

public interface Controller {
    String addAstronaut(String type, String astronautName) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;

    String addPlanet(String planetName, String... items);

    String retireAstronaut(String astronautName);

    String explorePlanet(String planetName);

    String report();
}
