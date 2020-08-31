package models.aquariums;


import models.decorations.Decoration;
import models.fish.Fish;
import common.ConstantMessages;
import common.ExceptionMessages;

import java.util.*;


public abstract class BaseAquarium implements Aquarium {


    private String name;
    // ⦁	All names are unique
    private int capacity;
    private List<Decoration> decorations;
    private List<Fish> fish;

    protected BaseAquarium(String name, int capacity) {
        setName(name);
        this.capacity = capacity;
        this.decorations = new ArrayList<>();
        this.fish = new ArrayList<>();

    }


    protected void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.AQUARIUM_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public int calculateComfort() {
        int comfortLevel = 0;
        for (Decoration decoration : this.decorations) {
            comfortLevel += decoration.getComfort();
        }
        return comfortLevel;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void addFish(Fish fish) {
        if (this.capacity <= this.fish.size()) {
            throw new IllegalArgumentException(ConstantMessages.NOT_ENOUGH_CAPACITY);
        }
        this.fish.add(fish);
    }

    @Override
    public void removeFish(Fish fish) {
        this.fish.remove(fish);
    }

    @Override
    public void addDecoration(Decoration decoration) {
        this.decorations.add(decoration);
    }

    @Override
    public void feed() {
        for (Fish fish1 : this.fish) {
            fish1.eat();
        }
    }

    @Override
    public String getInfo() {

        StringBuilder builder = new StringBuilder(this.getName()).append(" (").

                append(this.getClass().getSimpleName()).append("):").append(System.lineSeparator());
        builder.append("Fish: ");
        if (this.fish.isEmpty()) {
            builder.append("none").append(System.lineSeparator());
        } else {
            for (Fish fish1 : this.fish) {
                builder.append(fish1.getName()).append(" ");
            }
            builder.append(System.lineSeparator());
        }
        builder.append("Decorations: ").append(this.getDecorations().size()).append(System.lineSeparator());
        builder.append("Comfort: ").append(this.calculateComfort());

        return builder.toString().trim();
    }

    @Override
    public Collection<Fish> getFish() {
        return this.fish;
    }

    @Override
    public Collection<Decoration> getDecorations() {
        return this.decorations;
    }


}
