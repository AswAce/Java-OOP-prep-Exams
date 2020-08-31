package repositories;

import models.decorations.Decoration;

import java.util.ArrayList;
import java.util.List;

public class DecorationRepository implements repositories.Repository {
    private List<Decoration> decorations = new ArrayList<>();


    @Override
    public void add(Decoration decoration) {
        this.decorations.add(decoration);
    }

    @Override
    public boolean remove(Decoration decoration) {
        return this.decorations.remove(decoration);
    }

    @Override
    public Decoration findByType(String type) {

        for (Decoration decoration : decorations) {
            if (decoration.getClass().getSimpleName().equals(type)) {
                return decoration;
            }
        }
        return null;
    }
}
