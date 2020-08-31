package models.players;

import repositories.interfaces.CardRepository;

public class Advanced extends BasePlayer {
    private static final int defaultHealthPoints = 250;

    public Advanced(CardRepository cardRepository, String name) {
        super(cardRepository, name, defaultHealthPoints);
    }
}
