package core;

import common.ConstantMessages;
import common.ExceptionMessages;
import models.aquariums.Aquarium;
import models.aquariums.FreshwaterAquarium;
import models.aquariums.SaltwaterAquarium;
import models.decorations.Decoration;
import models.fish.Fish;
import repositories.DecorationRepository;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class ControllerImpl implements Controller {
    DecorationRepository decorations;
    List<Aquarium> aquariums;

    public ControllerImpl() {
        this.decorations = new DecorationRepository();
        this.aquariums = new ArrayList<>();
    }

    @Override
    public String addAquarium(String aquariumType, String aquariumName) {
        Aquarium aquarium = null;
        if (aquariumType.equals("SaltwaterAquarium")) {
            aquarium = new SaltwaterAquarium(aquariumName);
        } else if (aquariumType.equals("FreshwaterAquarium")) {
            aquarium = new FreshwaterAquarium(aquariumName);
        }
        if (aquarium != null && !this.aquariums.contains(aquarium)) {
            this.aquariums.add(aquarium);
            return String.format(ConstantMessages.SUCCESSFULLY_ADDED_AQUARIUM_TYPE, aquariumType);
        }


        throw new IllegalArgumentException(ExceptionMessages.INVALID_AQUARIUM_TYPE);
    }

    @Override
    public String addDecoration(String type) {
        try {
            Class<?> decoration = Class.forName("models.decorations." + type);
            this.decorations.add((Decoration) decoration.getConstructor().newInstance());
            return String.format(ConstantMessages.SUCCESSFULLY_ADDED_DECORATION_TYPE, type);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_DECORATION_TYPE);
        }

    }

    @Override
    public String insertDecoration(String aquariumName, String decorationType) {
        try {
            Class.forName("models.decorations." + decorationType);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(
                    String.format(ExceptionMessages.NO_DECORATION_FOUND, decorationType));
        }
        Aquarium aquarium = getRightAquarium(aquariumName);
        Decoration byType = this.decorations.findByType(decorationType);
        aquarium.addDecoration(byType);
        this.decorations.remove(byType);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_DECORATION_IN_AQUARIUM
                , decorationType, aquariumName);
    }

    @Override
    public String addFish(String aquariumName, String fishType, String fishName, String fishSpecies, double price) {

        Fish born = null;
        try {
            Class<?> newFIsh = Class.forName("models.fish." + fishType);
            born = (Fish) newFIsh.getConstructor(String.class, String.class, double.class).newInstance(fishName, fishSpecies, price);

        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_FISH_TYPE);
        }
        Aquarium aquarium = getRightAquarium(aquariumName);
        if (!aquarium.getClass().getSimpleName().
                startsWith(fishType.substring(0, fishType.length() - 5))) {
            return ConstantMessages.WATER_NOT_SUITABLE;
        }

        int capacity = 0;
        if (aquarium.getClass().getSimpleName().equals("FreshwaterAquarium")) {
            capacity = 50;
        }
        if (aquarium.getClass().getSimpleName().equals("SaltwaterAquarium")) {
            capacity = 25;
        }
        if (capacity - aquarium.getFish().size() > 0) {
            aquarium.addFish(born);
            return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FISH_IN_AQUARIUM, fishType, aquariumName);
        } else {
            return ConstantMessages.NOT_ENOUGH_CAPACITY;
        }


    }

    @Override
    public String feedFish(String aquariumName) {
        for (Aquarium aquarium : this.aquariums) {
            if (aquarium.getName().equals(aquariumName)) {
                aquarium.feed();
                return String.format(ConstantMessages.FISH_FED, aquarium.getFish().size());
            }
        }
        return null;
    }

    @Override
    public String calculateValue(String aquariumName) {
        Aquarium aquarium = getRightAquarium(aquariumName);
        double sum = 0;

        for (Fish fishPrice : aquarium.getFish()
        ) {
            sum += fishPrice.getPrice();
        }
        for (Decoration decoration : aquarium.getDecorations()) {
            sum += decoration.getPrice();

        }

        return String.format(ConstantMessages.VALUE_AQUARIUM, aquariumName, sum);
    }

    @Override
    public String report() {
        StringBuilder builder = new StringBuilder();
        for (Aquarium aquarium : this.aquariums) {
            builder.append(aquarium.getInfo()).append(System.lineSeparator());
        }
        return builder.toString().trim();
    }

    private Aquarium getRightAquarium(String aqriumName) {
        for (Aquarium aquarium : aquariums) {
            if (aquarium.getName().equals(aqriumName)) {
                return aquarium;
            }
        }
        return null;
    }
}
