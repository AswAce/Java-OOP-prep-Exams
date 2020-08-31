package motocrossWorldChampionship.repositories.interfaces;

import motocrossWorldChampionship.entities.interfaces.Rider;

public class RacerRepository extends RepositoryImpl<Rider> {

    @Override
    public Rider getByName(String name) {
        for (Rider rider : super.list) {
            if (rider.getName().equals(name)) {
                return rider;
            }
        }
        return null;
    }
}
