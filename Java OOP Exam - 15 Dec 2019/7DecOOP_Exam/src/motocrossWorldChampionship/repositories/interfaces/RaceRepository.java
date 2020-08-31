package motocrossWorldChampionship.repositories.interfaces;

import motocrossWorldChampionship.entities.interfaces.Race;

public class RaceRepository extends RepositoryImpl<Race> {

    @Override
    public Race getByName(String name) {
        for (Race race : super.list) {
            if (race.getName().equals(name)) {
                return race;
            }
        }
        return null;
    }
}
