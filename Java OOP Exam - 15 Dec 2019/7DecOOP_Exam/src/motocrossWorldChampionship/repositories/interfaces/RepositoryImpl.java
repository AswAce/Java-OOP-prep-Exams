package motocrossWorldChampionship.repositories.interfaces;

import motocrossWorldChampionship.entities.interfaces.Rider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RepositoryImpl<T> implements Repository<T> {

    protected List<T> list;

    public RepositoryImpl() {
        this.list = new ArrayList<>();
    }

    @Override
    public T getByName(String name) {
        return null;
    }

    @Override
    public Collection getAll() {
        return this.list;
    }

    @Override
    public void add(T model) {
        this.list.add(model);

    }

    @Override
    public boolean remove(T model) {
        return this.list.remove(model);
    }
}
