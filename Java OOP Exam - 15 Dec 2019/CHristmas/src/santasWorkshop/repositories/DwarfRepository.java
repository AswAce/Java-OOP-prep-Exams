package santasWorkshop.repositories;

import santasWorkshop.models.Dwarf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DwarfRepository implements Repository<Dwarf> {
    List<Dwarf> dwarfs;

    public DwarfRepository() {
        this.dwarfs = new ArrayList<>();
    }

    @Override
    public Collection<Dwarf> getModels() {
        return this.dwarfs;
    }

    @Override
    public void add(Dwarf model) {
        if (!this.dwarfs.contains(model)) {
            this.dwarfs.add(model);
        }
    }

    @Override
    public boolean remove(Dwarf model) {
        return this.dwarfs.remove(model);
    }

    @Override
    public Dwarf findByName(String name) {
        for (Dwarf dwarf : this.dwarfs) {
            if (dwarf.getName().equals(name)) {
                return dwarf;
            }
        }
        return null;
    }
}
