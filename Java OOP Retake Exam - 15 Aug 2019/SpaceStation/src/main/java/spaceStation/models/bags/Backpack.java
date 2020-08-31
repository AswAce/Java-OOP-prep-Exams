package spaceStation.models.bags;

import spaceStation.common.ConstantMessages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Backpack implements Bag {

    private List<String> items;

    public Backpack() {
        this.items = new ArrayList<>();
    }

    @Override
    public Collection<String> getItems() {
        return this.items;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (this.getItems().isEmpty()) {
            builder.append("none").append(System.lineSeparator());
        } else {
            builder.append(String.join(", ", (this.items)));
        }
        return String.format(ConstantMessages.REPORT_ASTRONAUT_BAG_ITEMS, builder.toString().trim());
    }
}
