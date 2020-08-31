package motocrossWorldChampionship.repositories.interfaces;

import motocrossWorldChampionship.entities.interfaces.Motorcycle;

public class MotorRepository extends RepositoryImpl<Motorcycle> {

    @Override
    public Motorcycle getByName(String name) {
        for (Motorcycle motorcycle : super.list) {
            if (motorcycle.getModel().equals(name)) {
                return motorcycle;
            }
        }
        return null;
    }
}
