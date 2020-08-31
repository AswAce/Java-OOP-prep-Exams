package santasWorkshop.repositories;

import santasWorkshop.models.Present;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PresentRepository implements Repository<Present> {

    List<Present> presents;

    public PresentRepository() {
        this.presents = new ArrayList<>();
    }

    @Override
    public Collection<Present> getModels() {
        return this.presents;
    }

    @Override
    public void add(Present model) {
        if (!this.presents.contains(model)) {
            this.presents.add(model);
        }
    }

    @Override
    public boolean remove(Present model) {
        return this.presents.remove(model);
    }

    @Override
    public Present findByName(String name) {
        for (Present present : this.presents) {
            if (present.getName().equals(name)) {
                return present;
            }
        }
        return null;
    }
}
