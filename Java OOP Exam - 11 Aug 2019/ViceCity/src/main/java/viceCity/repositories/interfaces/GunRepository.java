package viceCity.repositories.interfaces;

import viceCity.models.guns.Gun;

import java.util.*;


public class GunRepository implements Repository<Gun> {
    private List<Gun> models;

    public GunRepository() {
        this.models = new ArrayList<>();
    }

    @Override
    public Collection<Gun> getModels() {
        return this.models;
    }

    @Override
    public void add(Gun model) {
        if (!this.models.contains(model)) {
            this.models.add(model);
        }


    }

    @Override
    public boolean remove(Gun model) {
        return this.models.remove(model);
    }

    @Override
    public Gun find(String name) {

        for (Gun model : models) {
            if (model.getName().equals(name)) {
                return model;
            }
        }
        return null;
    }
}
